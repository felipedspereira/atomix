package com.felipe.atomix.common.configuration.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.felipe.atomix.common.configuration.AtomixProperties;
import io.atomix.core.Atomix;
import io.atomix.core.map.AtomicMap;
import io.atomix.primitive.Replication;
import io.atomix.protocols.backup.MultiPrimaryProtocol;
import io.atomix.protocols.backup.partition.PrimaryBackupPartitionGroup;

@Configuration
public class ClientConfiguration {
  @Autowired private AtomixProperties atomixProperties;

  @Value("${server.port}")
  private int port;

  private static final String MAP_NAME = "myMap"; 
  
  @Bean
  public AtomicMap<String, String> atomicMap(Atomix atomix) {
    return atomix
        .<String, String>atomicMapBuilder(MAP_NAME)
        .withProtocol(
            MultiPrimaryProtocol.builder(atomixProperties.getPartitionGroupName())
                .withBackups(2)
                .withReplication(Replication.ASYNCHRONOUS)
                .build())
        .build();
  }

  @Bean
  public Atomix atomix() {
    Atomix atomix =
        Atomix.builder()
            .withClusterId(atomixProperties.getClusterId())
            .withMulticastEnabled()
            .withMemberId(atomixProperties.getMemberName())
            .withHost("localhost")
            .withPort(port + 100)
            .withPartitionGroups(
                PrimaryBackupPartitionGroup.builder(atomixProperties.getPartitionGroupName())
                    .withNumPartitions(71)
                    .build())
            .build();

    atomix.start().join();

    return atomix;
  }
}

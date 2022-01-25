package com.felipe.atomix.common.configuration.management;

import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.felipe.atomix.common.configuration.AtomixProperties;
import io.atomix.core.Atomix;
import io.atomix.protocols.raft.partition.RaftPartitionGroup;

@Configuration
public class ManagementGroupConfiguration {
  @Autowired private AtomixProperties atomixProperties;

  @Value("${server.port}")
  private int port;

  @Bean
  public Atomix atomix() {
    List<String> memberNames=
        atomixProperties.getNodes().entrySet().stream()
            .map(Entry::getKey)
            .collect(Collectors.toList());
    
    Atomix atomix =
        Atomix.builder()
            .withClusterId(atomixProperties.getClusterId())
            .withMemberId(atomixProperties.getMemberName())
            .withMulticastEnabled()
            .withHost("localhost")
            .withPort(port + 100)
            .withManagementGroup(
                RaftPartitionGroup.builder("kakaka")
                    .withNumPartitions(1)
                    .withMembers(memberNames)
                    .withDataDirectory(new File("data/" + atomixProperties.getMemberName()))
                    .build())
            .build();

    atomix.start().join();

    return atomix;
  }
}

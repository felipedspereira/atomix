package com.felipe.atomix.common.configuration;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.atomix.cluster.Node;
import io.atomix.cluster.discovery.BootstrapDiscoveryProvider;
import io.atomix.core.Atomix;
import io.atomix.core.map.AtomicMap;
import io.atomix.core.profile.Profile;
import io.atomix.protocols.backup.MultiPrimaryProtocol;
import io.atomix.protocols.backup.partition.PrimaryBackupPartitionGroup;

@Configuration
public class AtomixConfiguration {
  @Autowired private AtomixProperties atomixProperties;

  @Value("${server.port}")
  private int port;

  public static final String MAP = "my-map";

  @Bean
  public AtomicMap<String, String> atomicMap(Atomix atomix) {
    return atomix
        .<String, String>atomicMapBuilder(MAP)
        .withProtocol(MultiPrimaryProtocol.builder().build())
        .build();
  }

  @Bean
  public Atomix atomix() {
    List<Node> nodes =
        atomixProperties.getNodes().entrySet().stream()
            .map(this::buildNode)
            .collect(Collectors.toList());

    Atomix atomix =
        Atomix.builder()
            .withClusterId("my-cluster")
            .withMemberId(atomixProperties.getMemberName())
            .withHost("localhost")
            .withPort(port + 100)
            .withMembershipProvider(BootstrapDiscoveryProvider.builder().withNodes(nodes).build())
            .addProfile(Profile.dataGrid())
            .withManagementGroup(
                PrimaryBackupPartitionGroup.builder("system").withNumPartitions(1).build())
            .withPartitionGroups(
                PrimaryBackupPartitionGroup.builder("data").withNumPartitions(72).build())
            .build();

    atomix.start().join();

    return atomix;
  }

  private Node buildNode(Entry<String, String> entry) {
    return Node.builder()
        .withId(entry.getKey())
        .withHost(entry.getValue().split(":")[0])
        .withPort(Integer.parseInt(entry.getValue().split(":")[1]))
        .build();
  }
}

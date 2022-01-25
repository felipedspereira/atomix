package com.felipe.atomix.common.configuration;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "atomix-cluster")
@Getter
@Setter
public class AtomixProperties {

  private Map<String, String> nodes;

  private String memberName;

  private String clusterId;
}

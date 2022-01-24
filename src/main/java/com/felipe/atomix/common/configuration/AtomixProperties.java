package com.felipe.atomix.common.configuration;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "atomix-cluster")
public class AtomixProperties {

  @Getter @Setter private Map<String, String> nodes;

  @Getter @Setter private String memberName;
}

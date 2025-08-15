package com.robbie.personaltools.infra.component.redis;

import org.springframework.stereotype.Component;

@Component
public class RedisKeyBuilder {
  public String accessToken(String token) {
    return "access:" + token;
  }
}

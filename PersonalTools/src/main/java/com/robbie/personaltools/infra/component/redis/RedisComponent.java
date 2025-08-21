package com.robbie.personaltools.infra.component.redis;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 主要提供一般使用 redis <br>
 * 像是 accessToken 取值
 */
@Component
@RequiredArgsConstructor
public class RedisComponent {
  private final StringRedisTemplate redisTemplate;

  public Map<Object, Object> hashGet(final String key) {
    return this.redisTemplate.opsForHash().entries(key);
  }
}

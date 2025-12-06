package com.robbie.backend.infra.dataprovider.accesstoken.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robbie.backend.infra.component.redis.RedisComponent;
import com.robbie.backend.infra.component.redis.RedisKeyBuilder;
import com.robbie.backend.infra.constant.ErrorCodeEnum;
import com.robbie.backend.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.backend.infra.dataprovider.accesstoken.TokenVerifier;
import com.robbie.backend.infra.dataprovider.accesstoken.model.AccessTokenInfo;
import com.robbie.backend.infra.exception.ValidException;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessTokenProvider implements TokenGetter, TokenVerifier {
  private final RedisComponent redisComponent;
  private final RedisKeyBuilder redisKeyBuilder;
  private final ObjectMapper objectMapper;

  private final ThreadLocal<AccessTokenInfo> tokenInfo = new ThreadLocal<>();

  @Override
  public AccessTokenInfo getTokenInfo() throws ValidException {
    AccessTokenInfo accessTokenInfo = this.tokenInfo.get();

    if (Objects.isNull(accessTokenInfo)) {
      throw new ValidException(ErrorCodeEnum.ACCESS_TOKEN_INVALID);
    }
    return accessTokenInfo;
  }

  @Override
  public AccessTokenInfo verify(String token) throws ValidException {
    try {
      AccessTokenInfo accessTokenInfo =
          Optional.ofNullable(token)
              .filter(t -> !t.isBlank())
              .map(t -> this.redisComponent.hashGet(this.redisKeyBuilder.accessToken(token)))
              .filter(m -> !m.isEmpty())
              .map(m -> this.objectMapper.convertValue(m, AccessTokenInfo.class))
              .orElseThrow();

      this.tokenInfo.set(accessTokenInfo);
      return accessTokenInfo;
    } catch (RuntimeException e) {
      this.tokenInfo.remove();
      throw new ValidException(e, ErrorCodeEnum.ACCESS_TOKEN_INVALID);
    }
  }
}

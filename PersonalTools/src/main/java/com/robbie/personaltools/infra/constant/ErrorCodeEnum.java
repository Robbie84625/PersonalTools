package com.robbie.personaltools.infra.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCodeEnum implements ErrorInfo {
  ACCESS_TOKEN_INVALID("access token 無效");

  private final String errorMessage;

  @Override
  public String getErrorCode() {
    return this.name();
  }

  @Override
  public String getErrorMessage() {
    return this.errorMessage;
  }
}

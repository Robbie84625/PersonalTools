package com.robbie.personaltools.infra.exception;

import com.robbie.personaltools.infra.model.ResultError;
import java.util.Optional;
import lombok.Getter;

@Getter
public class ValidException extends Exception {
  private final ResultError resultError;

  public ValidException(String errorCode, String errorMessage) {
    this.resultError = new ResultError(errorCode, errorMessage);
  }

  public ValidException(Throwable cause, String errorCode, String errorMessage) {
    super(cause);
    this.resultError = new ResultError(errorCode, errorMessage);
  }

  @Override
  public String getMessage() {
    return Optional.ofNullable(this.resultError).map(ResultError::toString).orElse("");
  }
}

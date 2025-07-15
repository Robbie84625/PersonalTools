package com.robbie.personaltools.infra.exception;

import com.robbie.personaltools.constant.ErrorInfo;
import com.robbie.personaltools.infra.model.ResultError;
import java.text.MessageFormat;
import java.util.Optional;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

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

  public ValidException(ErrorInfo error, String... messages) {
    this.resultError = this.toResultError(error, messages);
  }

  public ValidException(Throwable cause, ErrorInfo error, String... messages) {
    super(cause);
    this.resultError = this.toResultError(error, messages);
  }

  @Override
  public String getMessage() {
    return Optional.ofNullable(this.resultError).map(ResultError::toString).orElse("");
  }

  private ResultError toResultError(ErrorInfo error, String... messages) {
    String errorMessage = error.getErrorMessage();
    if (ArrayUtils.isNotEmpty(messages)) {
      MessageFormat fmt = new MessageFormat(errorMessage);
      errorMessage = fmt.format(messages);
    }
    return new ResultError(error.getErrorCode(), errorMessage);
  }
}

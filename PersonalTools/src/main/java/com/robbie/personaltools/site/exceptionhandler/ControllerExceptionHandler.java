package com.robbie.personaltools.site.exceptionhandler;

import com.robbie.personaltools.constant.ErrorInfo;
import com.robbie.personaltools.infra.model.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 共同的 Exception 處理類別<br>
 * 注意：要進到 controller 的 exception 才會接得到<br>
 * 如果是在 filter 拋出的錯不會跑到這邊
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.robbie.personaltools")
public class ControllerExceptionHandler {
  @ExceptionHandler(Exception.class)
  public Result<Void> handleException(final Exception ex) {
    this.logError(ex);
    return this.throwSystemError(ex.getMessage());
  }

  private void logError(Exception ex) {
    log.error("message:" + ex.getMessage(), ex);
  }

  private Result<Void> throwSystemError(String errorMessage) {
    return Result.fail(ErrorCodeEnum.SYSTEM_ERROR.name(), errorMessage);
  }

  @RequiredArgsConstructor
  private enum ErrorCodeEnum implements ErrorInfo {
    SYSTEM_ERROR("系統錯誤"),
    INPUT_DATA_INVALID("無效的{0}");

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
}

package com.robbie.personaltools.infra.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** 執行結果 */
@Data
public class Result<T> {
  /** 執行結果 */
  @Schema(description = "執行結果", example = "true")
  private boolean success;

  /** 錯誤資訊 */
  @Schema(description = "錯誤資訊")
  @JsonInclude(Include.NON_NULL)
  private ResultError error;

  /** 回應資訊 */
  @JsonInclude(Include.NON_NULL)
  private T data;

  public Result(T data) {
    this.data = data;
    this.success = true;
  }

  public Result(ResultError error) {
    this.error = error;
    this.success = false;
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(data);
  }

  public static Result<Object> empty() {
    return new Result<>(new Object());
  }

  public static Result<Void> fail(ResultError resultError) {
    return new Result<>(resultError);
  }

  public static Result<Void> fail(String errorCode, String errorMessage) {
    return new Result<>(new ResultError(errorCode, errorMessage));
  }
}

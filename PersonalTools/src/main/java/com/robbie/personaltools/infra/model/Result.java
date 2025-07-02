package com.robbie.personaltools.infra.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** 執行結果 */
@Data
public class Result<T> {
  @Schema(description = "執行結果",example = "true")
  private boolean success;



  /** 回應資訊 */
  @JsonInclude(Include.NON_NULL)
  private T data;
}

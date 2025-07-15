package com.robbie.personaltools.infra.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ResultError implements Serializable {
  /** 錯誤代碼 */
  @Schema(description = "錯誤代碼", example = "SYSTEM_ERROR")
  private final String errorCode;

  /** 錯誤訊息 */
  @Schema(description = "錯誤訊息", example = "系統錯誤")
  private final String errorMessage;
}

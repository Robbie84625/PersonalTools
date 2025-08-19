package com.robbie.personaltools.front.api.cheatmeal.updatebudget.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserBudgetRequest {
  /** 使用者設定的預算 */
  @NotNull(message = "預算不能為空")
  @Min(value = 0, message = "預算不能為負數")
  @Max(value = 1000, message = "預算不得超過1000")
  private Integer budget;
}

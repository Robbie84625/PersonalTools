package com.robbie.personaltools.front.api.cheatmeal.updateResetWeekday.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateResetWeekdayRequest {
  @NotNull(message = "星期不能為空")
  @Min(value = 1, message = "星期必須在1-7之間")
  @Max(value = 7, message = "星期必須在1-7之間")
  private Integer resetWeekday;
}

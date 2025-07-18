package com.robbie.personaltools.font.api.cheatmeal.createconsumption.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateConsumptionRequest {
  /** 作弊餐點名稱 */
  @NotBlank private String cheatMealName;

  /** 作弊餐點消耗點數 */
  @NotBlank private Integer cheatMealPoint;
}

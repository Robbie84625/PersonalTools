package com.robbie.personaltools.front.api.cheatmeal.createrecord.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateRecordRequest {
  /** 作弊餐點名稱 */
  @NotBlank private String cheatMealName;

  /** 作弊餐點消耗點數 */
  @NotBlank private Integer cheatMealPoint;

  /** 總預算 */
  @NotBlank private Integer totalBudget;

  /** 星期幾重置 */
  @NotBlank private Integer resetWeekday;
}

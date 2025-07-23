package com.robbie.personaltools.font.api.cheatmeal.getcheatmealitem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheatMealItemResponse {
  /** 作弊餐點名稱 */
  private String cheatMealName;

  /** 作弊餐點消耗點數 */
  private Integer cheatMealPoint;

  /** 剩餘點數 */
  private Integer afterConsumptionBudget;
}

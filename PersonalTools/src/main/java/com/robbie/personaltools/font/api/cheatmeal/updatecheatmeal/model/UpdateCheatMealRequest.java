package com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateCheatMealRequest {
  /** 作弊餐點名稱 */
  private String name;

  /** 作弊餐點等級 */
  private Integer level;

  /** 作弊餐點類別 */
  private String category;

  /** 作弊餐點消耗額度 */
  private Integer point;
}

package com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateCheatMealRequest {
  /** 作弊餐點名稱 */
  @NotBlank private String name;

  /** 作弊餐點等級 */
  @NotNull private Integer level;

  /** 作弊餐點類別 */
  @NotNull private String category;

  /** 作弊餐點消耗額度 */
  @NotNull private Integer point;
}

package com.robbie.personaltools.font.api.cheatmeal.getbudget.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetBudgetResponse {
  /** 使用者設定的預算 */
  private Integer budget;

  /** 剩餘預算 */
  private Integer remainingBudget;

  /** 已經使用預算 */
  private Integer spentBudget;

  /** 每周重置日 */
  private Integer weeklyResetDay;
}

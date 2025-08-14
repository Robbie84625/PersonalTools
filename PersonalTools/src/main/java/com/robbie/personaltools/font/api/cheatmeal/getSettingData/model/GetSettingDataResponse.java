package com.robbie.personaltools.font.api.cheatmeal.getSettingData.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetSettingDataResponse {
  /** 使用者名稱 */
  private String customerName;

  /** 使用者 Type */
  private String customerType;

  /** Google 電子郵件 */
  private String email;

  /** 使用者設定預算 */
  private Integer budget;

  /** 每周重置日 */
  private Integer resetWeekday;
}

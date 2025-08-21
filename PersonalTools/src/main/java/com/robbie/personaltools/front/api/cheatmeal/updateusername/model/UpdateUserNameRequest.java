package com.robbie.personaltools.front.api.cheatmeal.updateusername.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserNameRequest {
  /** 使用者名稱 */
  @NotBlank private String userName;
}

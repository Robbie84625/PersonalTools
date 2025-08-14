package com.robbie.personaltools.font.api.cheatmeal.getSettingData;

import com.robbie.personaltools.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import com.robbie.personaltools.infra.databases.entity.member.Member;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealBudgetPersistence;
import com.robbie.personaltools.middle.infrastructure.persistence.MemberPersistence;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetSettingDataFlow {

  private final MemberPersistence memberPersistence;
  private final CheatMealBudgetPersistence cheatMealBudgetPersistence;

  @Value("${customer.id}")
  private String customerId;

  public Result execute() throws ValidException {
    // 取得使用者資料
    Member member =
        this.memberPersistence
            .findByCustomerId(this.customerId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.CUSTOMER_NOT_EXIST));

    // 取得使用者設定的預算
    BudgetSetting budgetSetting =
        this.cheatMealBudgetPersistence
            .findByCustomerId(this.customerId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.CUSTOMER_NOT_EXIST));

    return Result.builder()
        .customerName(member.getName())
        .customerType(member.getUserType())
        .email(member.getEmail())
        .budget(budgetSetting.getBudget())
        .resetWeekday(budgetSetting.getResetWeekday())
        .build();
  }

  @Data
  @Builder
  public static class Result {
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

  @RequiredArgsConstructor
  public enum ErrorCodeEnum implements ErrorInfo {
    CUSTOMER_NOT_EXIST("用戶不存在");

    private final String errorMessage;

    @Override
    public String getErrorCode() {
      return this.name();
    }

    @Override
    public String getErrorMessage() {
      return this.errorMessage;
    }
  }
}

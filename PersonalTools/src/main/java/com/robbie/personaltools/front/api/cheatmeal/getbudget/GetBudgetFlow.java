package com.robbie.personaltools.front.api.cheatmeal.getbudget;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.ConsumptionRecord;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.AccountPersistence;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealBudgetPersistence;
import com.robbie.personaltools.middle.infrastructure.persistence.ConsumptionRecordPersistence;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetBudgetFlow {
  private final TokenGetter tokenGetter;
  private final AccountPersistence accountPersistence;
  private final CheatMealBudgetPersistence cheatMealBudgetPersistence;
  private final ConsumptionRecordPersistence consumptionRecordPersistence;

  public Result execute() throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    // 驗證使用者
    if (!this.accountPersistence.existsByUserId(userId)) {
      throw new ValidException(ErrorCodeEnum.USER_NOT_EXIST);
    }
    LocalDateTime weekStart = LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay();
    LocalDateTime weekEnd = weekStart.plusDays(7);
    // 取得使用者設定的預算
    Integer budget =
        this.cheatMealBudgetPersistence.findByUserIdAndCreatedAtBefore(userId, weekEnd).stream()
            .findFirst()
            .map(BudgetSetting::getBudget)
            .orElse(0);

    // 去紀錄取得餐點消耗額度並加總
    Integer totalConsumedPoint =
        this.consumptionRecordPersistence
            .findByUserIdAndWeekRange(userId, weekStart, weekEnd)
            .stream()
            .mapToInt(ConsumptionRecord::getMealPoint)
            .sum();

    return Result.builder()
        .budget(budget)
        .spentBudget(totalConsumedPoint)
        .remainingBudget(budget - totalConsumedPoint)
        .weeklyResetDay(7)
        .build();
  }

  @Data
  @Builder
  public static class Result {
    /** 使用者設定的預算 */
    private Integer budget;

    /** 已經使用預算 */
    private Integer spentBudget;

    /** 剩餘預算 */
    private Integer remainingBudget;

    /** 每周重置日 */
    private Integer weeklyResetDay;
  }

  @RequiredArgsConstructor
  public enum ErrorCodeEnum implements ErrorInfo {
    USER_NOT_EXIST("用戶不存在");

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

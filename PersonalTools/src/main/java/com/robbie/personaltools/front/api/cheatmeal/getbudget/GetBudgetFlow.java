package com.robbie.personaltools.front.api.cheatmeal.getbudget;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Record;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.RecordMeal;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealBudgetPersistence;
import com.robbie.personaltools.middle.infrastructure.persistence.RecordPersistence;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetBudgetFlow {
  private final CheatMealBudgetPersistence cheatMealBudgetPersistence;
  private final RecordPersistence recordPersistence;

  private final TokenGetter tokenGetter;

  public Result execute() throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    // 取得使用者設定的預算
    BudgetSetting budgetSetting =
        this.cheatMealBudgetPersistence
            .findByUserId(userId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.USER_NOT_EXIST));

    // 使用者設定預算
    Integer budget = budgetSetting.getBudget();

    List<Record> records =
        this.recordPersistence.findByUserIdAndDateBetweenStartAtAndEndAt(userId, LocalDate.now());

    Long recordId = records.get(0).getId();

    // 去紀錄取得餐點消耗額度並加總
    List<RecordMeal> recordMeals = this.recordPersistence.findByRecordId(recordId);
    Integer totalConsumedPoint = recordMeals.stream().mapToInt(RecordMeal::getMealPoint).sum();

    return Result.builder()
        .budget(budget)
        .spentBudget(totalConsumedPoint)
        .remainingBudget(budget - totalConsumedPoint)
        .weeklyResetDay(budgetSetting.getResetWeekday())
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

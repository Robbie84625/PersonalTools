package com.robbie.personaltools.front.api.cheatmeal.getbudget;

import com.robbie.personaltools.infra.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.RecordMeal;
import com.robbie.personaltools.infra.dataprovider.accesstoken.TokenGetter;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.AccountPersistence;
import com.robbie.personaltools.middle.infrastructure.persistence.ConsumptionRecordPersistence;
import java.time.DayOfWeek;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetBudgetFlow {
  private final TokenGetter tokenGetter;
  private final AccountPersistence accountPersistence;
  private final ConsumptionRecordPersistence consumptionRecordPersistence;

  public Result execute() throws ValidException {
    String userId = this.tokenGetter.getTokenInfo().getUserId();

    LocalDate weekStart = LocalDate.now().with(DayOfWeek.MONDAY);

    // 取得使用者設定的預算
    Integer budget =
        this.accountPersistence
            .findByUserId(userId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.USER_NOT_EXIST))
            .getBudget();

    Pageable pageable = PageRequest.of(0, 1);

    // 去紀錄取得餐點消耗額度並加總
    Integer totalConsumedPoint =
        this.consumptionRecordPersistence
            .findByUserIdOrderByCreatedAtDesc(userId, pageable)
            .stream()
            .filter(record -> record.getWeekStart().isEqual(weekStart))
            .findFirst()
            .map(
                record ->
                    this.consumptionRecordPersistence.findByRecordId(record.getId()).stream()
                        .mapToInt(RecordMeal::getMealPoint)
                        .sum())
            .orElse(0);

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

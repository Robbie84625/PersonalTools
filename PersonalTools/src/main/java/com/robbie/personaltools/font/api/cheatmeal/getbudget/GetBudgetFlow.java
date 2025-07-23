package com.robbie.personaltools.font.api.cheatmeal.getbudget;

import com.robbie.personaltools.constant.ErrorInfo;
import com.robbie.personaltools.font.api.cheatmeal.getcheatmealitem.GetCheatMealItemFlow.Result;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.BudgetSetting;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Consumption;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.CheatMealBudgetRepository;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.ConsumptionRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetBudgetFlow {
  private final CheatMealBudgetRepository cheatMealBudgetRepository;
  private final ConsumptionRepository consumptionRepository;

  @Value("{customer.id}")
  private String customerId;

  public Result execute() throws ValidException {
    // 取得使用者設定的額度
    BudgetSetting budgetSetting =
        this.cheatMealBudgetRepository
            .findByCustomerId(this.customerId)
            .orElseThrow(() -> new ValidException(ErrorCodeEnum.CUSTOMER_NOT_EXIST));

    // 使用者設定預算
    Integer budget = budgetSetting.getBudget();

    LocalDateTime startDateTime = budgetSetting.getCycleStartDate().atStartOfDay();
    LocalDateTime endDateTime = budgetSetting.getCycleEndDate().plusDays(1).atStartOfDay();

    // 去消費明細取得以消費額度並加總
    List<Consumption> consumptions =
        this.consumptionRepository.findAllByConsumedAtBetween(
            this.customerId, startDateTime, endDateTime);

    Integer totalConsumedPoint =
        consumptions.stream().mapToInt(Consumption::getPointsConsumed).sum();

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

package com.robbie.personaltools.font.api.cheatmeal.getcheatmealitem;

import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.CheatMealBudgetRepository;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.ConsumptionRepository;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCheatMealItemFlow {

  private final CheatMealBudgetRepository cheatMealBudgetRepository;
  private final ConsumptionRepository consumptionRepository;

  @Value("{customer.id}")
  private String customerId;

  public Result execute(Command command) throws ValidException {
    //  預先計算剩下多少額度
    Integer remainingQuota = command.getCurrentRemainingBudget() - command.getCheatMealPoint();

    return Result.builder()
        .cheatMealName(command.getCheatMealName())
        .cheatMealPoint(command.getCheatMealPoint())
        .afterConsumptionBudget(remainingQuota)
        .build();
  }

  @Builder
  @Getter
  public static class Command {
    /** 作弊餐點名稱 */
    private String cheatMealName;

    /** 作弊餐點消耗額度 */
    private Integer cheatMealPoint;

    /** 當前剩餘點數 */
    private Integer currentRemainingBudget;
  }

  @Data
  @Builder
  public static class Result {
    /** 作弊餐點名稱 */
    private String cheatMealName;

    /** 作弊餐點消耗點數 */
    private Integer cheatMealPoint;

    /** 消耗後剩餘點數 */
    private Integer afterConsumptionBudget;
  }
}

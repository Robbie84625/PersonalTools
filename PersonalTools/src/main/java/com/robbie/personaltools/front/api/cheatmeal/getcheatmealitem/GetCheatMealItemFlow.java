package com.robbie.personaltools.front.api.cheatmeal.getcheatmealitem;

import com.robbie.personaltools.infra.exception.ValidException;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCheatMealItemFlow {

  public Result execute(Command command) throws ValidException {
    //  預先計算剩下多少額度
    Integer remainingBudget = command.getCurrentRemainingBudget() - command.getCheatMealPoint();

    return Result.builder()
        .cheatMealName(command.getCheatMealName())
        .cheatMealPoint(command.getCheatMealPoint())
        .afterConsumptionBudget(remainingBudget)
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

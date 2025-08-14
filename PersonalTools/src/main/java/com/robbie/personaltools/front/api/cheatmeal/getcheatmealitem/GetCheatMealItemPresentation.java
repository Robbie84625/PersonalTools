package com.robbie.personaltools.front.api.cheatmeal.getcheatmealitem;

import com.robbie.personaltools.front.api.cheatmeal.getcheatmealitem.GetCheatMealItemFlow.Command;
import com.robbie.personaltools.front.api.cheatmeal.getcheatmealitem.GetCheatMealItemFlow.Result;
import com.robbie.personaltools.front.api.cheatmeal.getcheatmealitem.model.GetCheatMealItemResponse;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("getCheatMealItemPresentation")
@RequiredArgsConstructor
public class GetCheatMealItemPresentation {
  public final GetCheatMealItemFlow getCheatMealItemFlow;

  public GetCheatMealItemResponse execute(
      String cheatMealName, Integer cheatMealPoint, Integer currentRemainingBudget)
      throws ValidException {
    Result result =
        this.getCheatMealItemFlow.execute(
            Command.builder()
                .cheatMealName(cheatMealName)
                .cheatMealPoint(cheatMealPoint)
                .currentRemainingBudget(currentRemainingBudget)
                .build());
    return GetCheatMealItemResponse.builder()
        .cheatMealName(result.getCheatMealName())
        .cheatMealPoint(result.getCheatMealPoint())
        .afterConsumptionBudget(result.getAfterConsumptionBudget())
        .build();
  }
}

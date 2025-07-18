package com.robbie.personaltools.font.api.cheatmeal.getbudget;

import com.robbie.personaltools.font.api.cheatmeal.getbudget.GetBudgetFlow.Result;
import com.robbie.personaltools.font.api.cheatmeal.getbudget.model.GetBudgetResponse;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("getBudgetPresentation")
@RequiredArgsConstructor
public class GetBudgetPresentation {
  private final GetBudgetFlow getBudgetFlow;

  public GetBudgetResponse execute() throws ValidException {
    Result result = this.getBudgetFlow.execute();

    return GetBudgetResponse.builder()
        .budget(result.getBudget())
        .remainingBudget(result.getRemainingBudget())
        .build();
  }
}

package com.robbie.personaltools.front.api.cheatmeal.updatebudget;

import com.robbie.personaltools.front.api.cheatmeal.updatebudget.UpdateUserBudgetFlow.Command;
import com.robbie.personaltools.front.api.cheatmeal.updatebudget.model.UpdateUserBudgetRequest;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("updateUserBudgetPresentation")
@RequiredArgsConstructor
public class UpdateUserBudgetPresentation {
  private UpdateUserBudgetFlow updateUserBudgetFlow;

  public void execute(UpdateUserBudgetRequest request) throws ValidException {

    this.updateUserBudgetFlow.execute(Command.builder().budget(request.getBudget()).build());
  }
}

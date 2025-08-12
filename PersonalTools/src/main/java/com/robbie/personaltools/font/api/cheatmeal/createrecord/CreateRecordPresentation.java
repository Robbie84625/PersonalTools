package com.robbie.personaltools.font.api.cheatmeal.createrecord;

import com.robbie.personaltools.font.api.cheatmeal.createrecord.CreateRecordFlow.Command;
import com.robbie.personaltools.font.api.cheatmeal.createrecord.model.CreateRecordRequest;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("createRecordPresentation")
@RequiredArgsConstructor
public class CreateRecordPresentation {
  private final CreateRecordFlow createRecordFlow;

  public void execute(CreateRecordRequest request) throws ValidException {
    this.createRecordFlow.execute(
        Command.builder()
            .cheatMealName(request.getCheatMealName())
            .cheatMealPoint(request.getCheatMealPoint())
            .totalBudget(request.getTotalBudget())
            .resetWeekday(request.getResetWeekday())
            .build());
  }
}

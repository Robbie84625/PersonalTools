package com.robbie.personaltools.font.api.cheatmeal.createconsumption;

import com.robbie.personaltools.font.api.cheatmeal.createconsumption.CreateConsumptionFlow.Command;
import com.robbie.personaltools.font.api.cheatmeal.createconsumption.model.CreateConsumptionRequest;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("createConsumptionPresentation")
@RequiredArgsConstructor
public class CreateConsumptionPresentation {
  private final CreateConsumptionFlow createConsumptionFlow;

  public void execute(CreateConsumptionRequest request) throws ValidException {
    this.createConsumptionFlow.execute(
        Command.builder()
            .cheatMealName(request.getCheatMealName())
            .cheatMealPoint(request.getCheatMealPoint())
            .build());
  }
}

package com.robbie.personaltools.font.api.cheatmeal.createcheatmeal;

import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.CreateCheatMealFlow.Command;
import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.model.CreateCheatMealRequest;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("createCheatMealPresentation")
@RequiredArgsConstructor
public class CreateCheatMealPresentation {
  private final CreateCheatMealFlow createCheatMealFlow;

  public void execute(CreateCheatMealRequest request) throws ValidException {
    this.createCheatMealFlow.execute(
        Command.builder()
            .name(request.getName())
            .level(request.getLevel())
            .point(request.getPoint())
            .category(request.getCategory())
            .build());
  }
}

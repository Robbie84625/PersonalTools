package com.robbie.personaltools.font.api.cheatmeal.createcheatmeal;

import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.CreateCheatMealItemFlow.Command;
import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.model.CreateCheatMealItemRequest;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("createCheatMealItemPresentation")
@RequiredArgsConstructor
public class CreateCheatMealItemPresentation {
  private final CreateCheatMealItemFlow createCheatMealItemFlow;

  public void execute(CreateCheatMealItemRequest request) throws ValidException {
    this.createCheatMealItemFlow.execute(
        Command.builder()
            .name(request.getName())
            .level(request.getLevel())
            .point(request.getPoint())
            .category(request.getCategory())
            .build());
  }
}

package com.robbie.backend.front.api.cheatmeal.createcheatmeal;

import com.robbie.backend.front.api.cheatmeal.createcheatmeal.CreateCheatMealItemFlow.Command;
import com.robbie.backend.front.api.cheatmeal.createcheatmeal.model.CreateCheatMealItemRequest;
import com.robbie.backend.infra.exception.ValidException;
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

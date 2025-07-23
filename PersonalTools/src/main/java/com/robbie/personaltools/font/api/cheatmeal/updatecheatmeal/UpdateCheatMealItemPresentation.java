package com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal;

import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.UpdateCheatMealItemFlow.Command;
import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.model.UpdateCheatMealItemRequest;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("updateCheatMealItemPresentation")
@RequiredArgsConstructor
public class UpdateCheatMealItemPresentation {
  private final UpdateCheatMealItemFlow updateCheatMealItemFlow;

  public void execute(Long id, UpdateCheatMealItemRequest request) throws ValidException {
    this.updateCheatMealItemFlow.execute(
        Command.builder()
            .id(id)
            .name(request.getName())
            .level(request.getLevel())
            .point(request.getPoint())
            .category(request.getCategory())
            .build());
  }
}

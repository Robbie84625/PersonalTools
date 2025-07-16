package com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal;

import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.UpdateCheatMealFlow.Command;
import com.robbie.personaltools.font.api.cheatmeal.updatecheatmeal.model.UpdateCheatMealRequest;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("updateCheatMealPresentation")
@RequiredArgsConstructor
public class UpdateCheatMealPresentation {
  private final UpdateCheatMealFlow updateCheatMealFlow;

  public void execute(Long id, UpdateCheatMealRequest request) throws ValidException {
    this.updateCheatMealFlow.execute(
        Command.builder()
            .id(id)
            .name(request.getName())
            .level(request.getLevel())
            .point(request.getPoint())
            .category(request.getCategory())
            .build());
  }
}

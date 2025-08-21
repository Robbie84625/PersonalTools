package com.robbie.personaltools.front.api.cheatmeal.updateResetWeekday;

import com.robbie.personaltools.front.api.cheatmeal.updateResetWeekday.UpdateResetWeekdayFlow.Command;
import com.robbie.personaltools.front.api.cheatmeal.updateResetWeekday.model.UpdateResetWeekdayRequest;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("updateResetWeekdayPresentation")
@RequiredArgsConstructor
public class UpdateResetWeekdayPresentation {
  private final UpdateResetWeekdayFlow updateResetWeekdayFlow;

  public void execute(UpdateResetWeekdayRequest request) throws ValidException {
    this.updateResetWeekdayFlow.execute(
        Command.builder().resetWeekday(request.getResetWeekday()).build());
  }
}

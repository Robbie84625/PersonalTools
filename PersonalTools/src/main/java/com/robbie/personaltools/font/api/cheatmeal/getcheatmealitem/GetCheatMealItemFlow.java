package com.robbie.personaltools.font.api.cheatmeal.getcheatmealitem;

import com.robbie.personaltools.font.api.cheatmeal.createcheatmeal.CreateCheatMealItemFlow.Command;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCheatMealItemFlow {
  public void execute(Command command) throws ValidException {}
}

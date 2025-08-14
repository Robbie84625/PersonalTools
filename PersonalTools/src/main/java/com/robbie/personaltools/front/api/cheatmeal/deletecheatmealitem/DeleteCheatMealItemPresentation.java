package com.robbie.personaltools.front.api.cheatmeal.deletecheatmealitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("deleteCheatMealItemPresentation")
@RequiredArgsConstructor
public class DeleteCheatMealItemPresentation {

  private final DeleteCheatMealItemFlow deleteCheatMealItemFlow;

  public void execute(Long cheatMealId) throws Exception {
    this.deleteCheatMealItemFlow.execute(cheatMealId);
  }
}

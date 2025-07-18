package com.robbie.personaltools.font.api.cheatmeal.getcheatmealitem;

import com.robbie.personaltools.font.api.cheatmeal.getcheatmealitem.model.GetCheatMealItemResponse;
import com.robbie.personaltools.infra.exception.ValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("getCheatMealItemPresentation")
@RequiredArgsConstructor
public class GetCheatMealItemPresentation {
  public GetCheatMealItemResponse execute(Long id) throws ValidException {
    return null;
  }
}

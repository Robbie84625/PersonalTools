package com.robbie.personaltools.font.api.cheatmeal.deletecheatmealitem;

import com.robbie.personaltools.constant.ErrorInfo;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.CheatMealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteCheatMealItemFlow {
  private final CheatMealRepository cheatMealRepository;

  public void execute(Long cheatMealId) throws ValidException {
    if (cheatMealId == null) {
      throw new ValidException(ErrorCodeEnum.CHEAT_MEAL_ID_NOT_EXIST);
    }

    this.cheatMealRepository.deleteCheatMeal(cheatMealId);
  }

  @RequiredArgsConstructor
  public enum ErrorCodeEnum implements ErrorInfo {
    CHEAT_MEAL_ID_NOT_EXIST("作弊餐 ID 不存在");

    private final String errorMessage;

    @Override
    public String getErrorCode() {
      return this.name();
    }

    @Override
    public String getErrorMessage() {
      return this.errorMessage;
    }
  }
}

package com.robbie.personaltools.front.api.cheatmeal.updatecheatmeal;

import com.robbie.personaltools.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.infrastructure.persistence.CheatMealPersistence;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateCheatMealItemFlow {

  private final CheatMealPersistence cheatMealPersistence;

  public void execute(Command command) throws ValidException {

    this.validateCommand(command);

    Meal updateMeal = new Meal();
    updateMeal.setId(command.getId());
    updateMeal.setName(command.getName());
    updateMeal.setLevel(command.getLevel());
    updateMeal.setPoint(command.getPoint());
    updateMeal.setCategory(command.getCategory());

    this.cheatMealPersistence.saveCheatMeal(updateMeal);
  }

  private void validateCommand(Command command) throws ValidException {
    if (StringUtils.isBlank(command.getName())) {
      throw new ValidException(ErrorCodeEnum.CHEAT_MEAL_NAME_BLANK);
    }
    if (command.getLevel() <= 0) {
      throw new ValidException(ErrorCodeEnum.CHEAT_MEAL_LEVEL_INVALID);
    }
    if (command.getPoint() <= 0) {
      throw new ValidException(ErrorCodeEnum.CHEAT_MEAL_POINT_INVALID);
    }
    if (StringUtils.isBlank(command.getCategory())) {
      throw new ValidException(ErrorCodeEnum.CHEAT_MEAL_CATEGORY_BLANK);
    }
  }

  @Builder
  @Getter
  public static class Command {
    /** 作弊餐點的 id */
    private Long id;

    /** 作弊餐點名稱 */
    private String name;

    /** 作弊餐點等級 */
    private Integer level;

    /** 作弊餐點消耗額度 */
    private Integer point;

    /** 作弊餐點類別 */
    private String category;
  }

  @RequiredArgsConstructor
  public enum ErrorCodeEnum implements ErrorInfo {
    CHEAT_MEAL_NAME_BLANK("餐點名稱不能為空"),
    CHEAT_MEAL_LEVEL_INVALID("等級不能小於零"),
    CHEAT_MEAL_CATEGORY_BLANK("分類不能為空"),
    CHEAT_MEAL_POINT_INVALID("消耗點數必須為正數");

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

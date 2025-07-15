package com.robbie.personaltools.font.api.cheatmeal.createcheatmeal;

import com.robbie.personaltools.constant.ErrorInfo;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import com.robbie.personaltools.infra.exception.ValidException;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.CheatMealRepository;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCheatMealFlow {

  private final CheatMealRepository cheatMealRepository;

  public void execute(Command command) throws ValidException {

    Optional<Meal> existingMeal = this.cheatMealRepository.findCheatMealByName(command.getName());
    if (existingMeal.isPresent()) {
      throw new ValidException(ErrorCodeEnum.DuplicateCheatMeal);
    }

    this.cheatMealRepository.saveCheatMeal(this.createCheatMeal(command));
  }

  @Builder
  @Getter
  public static class Command {
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
    DuplicateCheatMeal("作弊餐重複新增");

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

  private Meal createCheatMeal(Command command) {
    Meal meal = new Meal();
    meal.setName(command.getName());
    meal.setLevel(command.getLevel());
    meal.setCategory(command.getCategory());
    meal.setPoint(command.getPoint());
    return meal;
  }
}

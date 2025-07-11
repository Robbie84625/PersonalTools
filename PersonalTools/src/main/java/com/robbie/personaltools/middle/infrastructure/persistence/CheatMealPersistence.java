package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.MealDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.CheatMealRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheatMealPersistence implements CheatMealRepository {
  private final MealDao mealDao;

  @Override
  public void saveCheatMeal(Meal meal) {
    this.mealDao.save(meal);
  }

  @Override
  public Optional<Meal> findCheatMealByName(String name) {
    return this.mealDao.findByName(name);
  }
}

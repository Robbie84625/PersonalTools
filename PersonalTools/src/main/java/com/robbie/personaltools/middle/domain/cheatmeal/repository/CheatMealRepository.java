package com.robbie.personaltools.middle.domain.cheatmeal.repository;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import java.util.Optional;

public interface CheatMealRepository {
  void saveCheatMeal(Meal meal);

  Optional<Meal> findCheatMealByName(String name);
}

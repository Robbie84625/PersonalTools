package com.robbie.personaltools.middle.domain.cheatmeal.repository;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CheatMealRepository {
  void saveCheatMeal(Meal meal);

  Optional<Meal> findCheatMealByName(String name);

  Page<Meal> findAll(Pageable pageable);

  Page<Meal> findByKeywordContaining(String keyword, Pageable pageable);

  Page<Meal> findByCategory(String category, Pageable pageable);

  Page<Meal> findByCategoryAndNameContaining(String category, String keyword, Pageable pageable);

  void deleteCheatMeal(Long cheatMealId);
}

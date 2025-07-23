package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.MealDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.CheatMealRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  @Override
  public Page<Meal> findAll(Pageable pageable) {
    return this.mealDao.findAll(pageable);
  }

  @Override
  public Page<Meal> findByKeywordContaining(String keyword, Pageable pageable) {
    return this.mealDao.findByNameContaining(keyword, pageable);
  }

  @Override
  public Page<Meal> findByCategory(String category, Pageable pageable) {
    return this.mealDao.findByCategory(category, pageable);
  }

  @Override
  public Page<Meal> findByCategoryAndNameContaining(
      String category, String keyword, Pageable pageable) {
    return this.mealDao.findByCategoryAndNameContaining(category, keyword, pageable);
  }
}

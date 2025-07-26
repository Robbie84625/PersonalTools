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
  public Optional<Meal> findByCustomerIdAndMealName(String customerId, String name) {
    return this.mealDao.findByCustomerIdAndName(customerId, name);
  }

  @Override
  public Page<Meal> findAllByCustomerId(String customerId, Pageable pageable) {
    return this.mealDao.findAllByCustomerId(customerId, pageable);
  }

  @Override
  public Page<Meal> findByCustomerIdAndKeywordContaining(
      String customerId, String keyword, Pageable pageable) {
    return this.mealDao.findByCustomerIdAndNameContaining(customerId, keyword, pageable);
  }

  @Override
  public Page<Meal> findByCustomerIdAndCategory(
      String customerId, String category, Pageable pageable) {
    return this.mealDao.findByCustomerIdAndCategory(customerId, category, pageable);
  }

  @Override
  public Page<Meal> findByCustomerIdCategoryAndNameContaining(
      String customerId, String category, String keyword, Pageable pageable) {
    return this.mealDao.findByCustomerIdAndCategoryAndNameContaining(
        customerId, category, keyword, pageable);
  }

  @Override
  public int deleteByCustomerIdAndId(String customerId, Long cheatMealId) {
    return this.mealDao.deleteByCustomerIdAndId(customerId, cheatMealId);
  }
}

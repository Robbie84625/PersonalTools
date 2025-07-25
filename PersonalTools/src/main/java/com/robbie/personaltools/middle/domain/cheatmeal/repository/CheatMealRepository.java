package com.robbie.personaltools.middle.domain.cheatmeal.repository;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CheatMealRepository {
  void saveCheatMeal(Meal meal);

  Optional<Meal> findByCustomerIdAndMealName(String customerId, String name);

  Page<Meal> findAllByCustomerId(String customerId, Pageable pageable);

  Page<Meal> findByCustomerIdAndKeywordContaining(
      String customerId, String keyword, Pageable pageable);

  Page<Meal> findByCustomerIdAndCategory(String customerId, String category, Pageable pageable);

  Page<Meal> findByCustomerIdCategoryAndNameContaining(
      String customerId, String category, String keyword, Pageable pageable);

  int deleteByCustomerIdAndId(String customerId, Long cheatMealId);
}

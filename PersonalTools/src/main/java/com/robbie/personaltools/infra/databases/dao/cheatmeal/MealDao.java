package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealDao extends JpaRepository<Meal, Long> {
  Optional<Meal> findByCustomerIdAndName(String customerId, String name);

  Page<Meal> findAllByCustomerId(String customerId, Pageable pageable);

  Page<Meal> findByCustomerIdAndNameContaining(
      String customerId, String keyword, Pageable pageable);

  Page<Meal> findByCustomerIdAndCategory(String customerId, String category, Pageable pageable);

  Page<Meal> findByCustomerIdAndCategoryAndNameContaining(
      String customerId, String category, String keyword, Pageable pageable);

  int deleteByCustomerIdAndId(String customerId, Long cheatMealId);
}

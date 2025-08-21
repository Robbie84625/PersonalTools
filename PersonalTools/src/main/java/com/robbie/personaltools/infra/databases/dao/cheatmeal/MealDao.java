package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealDao extends JpaRepository<Meal, Long> {
  Optional<Meal> findByUserIdAndName(String userId, String name);

  Page<Meal> findAllByUserId(String userId, Pageable pageable);

  Page<Meal> findByUserIdAndNameContaining(String userId, String keyword, Pageable pageable);

  Page<Meal> findByUserIdAndCategory(String userId, String category, Pageable pageable);

  Page<Meal> findByUserIdAndCategoryAndNameContaining(
      String userId, String category, String keyword, Pageable pageable);

  int deleteByUserIdAndId(String userId, Long cheatMealId);
}

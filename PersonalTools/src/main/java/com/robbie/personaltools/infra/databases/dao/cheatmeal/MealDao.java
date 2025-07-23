package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealDao extends JpaRepository<Meal, Long> {
  Optional<Meal> findByName(String name);

  Page<Meal> findByNameContaining(String keyword, Pageable pageable);

  Page<Meal> findByCategory(String category, Pageable pageable);

  Page<Meal> findByCategoryAndNameContaining(String category, String keyword, Pageable pageable);
}

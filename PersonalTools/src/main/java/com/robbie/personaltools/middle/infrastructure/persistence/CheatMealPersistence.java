package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.MealDao;
import com.robbie.personaltools.middle.domain.cheatmeal.repository.CheatMealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheatMealPersistence implements CheatMealRepository {
  private final MealDao mealDao;
}

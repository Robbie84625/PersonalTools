package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.MealDao;
import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheatMealPersistence {
  private final MealDao mealDao;

  public void saveCheatMeal(Meal meal) {
    this.mealDao.save(meal);
  }

  public Optional<Meal> findByUserIdAndMealName(String userId, String name) {
    return this.mealDao.findByUserIdAndName(userId, name);
  }

  public Page<Meal> findMeals(String userId, String keyword, String category, Pageable pageable) {
    // 沒有任何搜尋條件
    if (StringUtils.isBlank(keyword) && StringUtils.isBlank(category)) {
      return this.mealDao.findAllByUserId(userId, pageable);
    }
    // 只有關鍵字
    if (StringUtils.isNotBlank(keyword) && StringUtils.isBlank(category)) {
      return this.mealDao.findByUserIdAndNameContaining(userId, keyword, pageable);
    }
    // 只有分類
    if (StringUtils.isBlank(keyword) && StringUtils.isNotBlank(category)) {
      return this.mealDao.findByUserIdAndCategory(userId, category, pageable);
    }
    // 兩個條件都有
    return this.mealDao.findByUserIdAndCategoryAndNameContaining(
        userId, category, keyword, pageable);
  }

  public int deleteByUserIdAndId(String userId, Long cheatMealId) {
    return this.mealDao.deleteByUserIdAndId(userId, cheatMealId);
  }
}

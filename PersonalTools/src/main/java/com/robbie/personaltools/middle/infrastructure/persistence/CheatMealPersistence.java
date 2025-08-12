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

  public Optional<Meal> findByCustomerIdAndMealName(String customerId, String name) {
    return this.mealDao.findByCustomerIdAndName(customerId, name);
  }

  public Page<Meal> findMeals(
      String customerId, String keyword, String category, Pageable pageable) {
    // 沒有任何搜尋條件
    if (StringUtils.isBlank(keyword) && StringUtils.isBlank(category)) {
      return this.mealDao.findAllByCustomerId(customerId, pageable);
    }
    // 只有關鍵字
    if (StringUtils.isNotBlank(keyword) && StringUtils.isBlank(category)) {
      return this.mealDao.findByCustomerIdAndNameContaining(customerId, keyword, pageable);
    }
    // 只有分類
    if (StringUtils.isBlank(keyword) && StringUtils.isNotBlank(category)) {
      return this.mealDao.findByCustomerIdAndCategory(customerId, category, pageable);
    }
    // 兩個條件都有
    return this.mealDao.findByCustomerIdAndCategoryAndNameContaining(
        customerId, category, keyword, pageable);
  }

  public int deleteByCustomerIdAndId(String customerId, Long cheatMealId) {
    return this.mealDao.deleteByCustomerIdAndId(customerId, cheatMealId);
  }
}

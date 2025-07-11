package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.cheatmeal.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealDao extends JpaRepository<Meal, Integer> {}

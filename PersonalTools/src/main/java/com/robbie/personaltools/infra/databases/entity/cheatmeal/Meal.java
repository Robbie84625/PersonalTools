package com.robbie.personaltools.infra.databases.entity.cheatmeal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "meal", schema = "cheat_meal")
@Data
public class Meal {
  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 作弊餐名稱 */
  private String name;

  /** 作弊餐等級 */
  private Integer level;

  /** 作弊餐點消耗額度 */
  private Integer point;

  /** 作弊餐類別 */
  private String category;
}

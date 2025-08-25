package com.robbie.personaltools.infra.databases.entity.cheatmeal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "consumption_record", schema = "cheat_meal")
@Data
public class RecordMeal {
  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** record.id */
  @Column(name = "record_id")
  private Long recordId;

  /** 作弊餐名稱 */
  @Column(name = "meal_name")
  private String mealName;

  /** 作弊餐點消耗額度 */
  @Column(name = "meal_point")
  private Integer mealPoint;

  /** 消費日期 */
  @Column(name = "consumed_at")
  private LocalDateTime consumedAt;
}

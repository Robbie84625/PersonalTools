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
@Table(name = "record_meal", schema = "cheat_meal")
@Data
public class RecordMeal {
  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 餐點名稱 */
  @Column(name = "meal_name")
  private String mealName;

  /** 餐點點數 */
  @Column(name = "meal_point")
  private Integer mealPoint;

  /** consumption_record 資料表 id */
  @Column(name = "record_id")
  private Long recordId;

  /** 建立時間 */
  @Column(name = "consumed_at")
  private LocalDateTime consumedAt;
}

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
@Table(name = "consumption", schema = "cheat_meal")
@Data
public class Consumption {
  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 會員編號 */
  @Column(name = "customer_id")
  private String customerId;

  /** 作弊餐名稱 */
  @Column(name = "meal_name")
  private String mealName;

  /** 消費額度 */
  @Column(name = "points_consumed")
  private Integer pointsConsumed;

  /** 消費日期 */
  @Column(name = "consumed_at")
  private LocalDateTime consumedAt;
}

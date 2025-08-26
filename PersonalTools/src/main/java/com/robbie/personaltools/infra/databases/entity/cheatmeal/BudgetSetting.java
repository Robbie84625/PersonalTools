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
@Table(name = "budget_setting", schema = "cheat_meal")
@Data
public class BudgetSetting {
  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 會員編號 */
  @Column(name = "user_id")
  private String userId;

  /** 使用者作弊餐總額度 */
  private Integer budget;

  /** 重置週期日 (1=週一, 2=週二, ..., 7=週日) */
  @Column(name = "created_at")
  private LocalDateTime createdAt;
}

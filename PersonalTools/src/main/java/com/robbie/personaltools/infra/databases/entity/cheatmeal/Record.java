package com.robbie.personaltools.infra.databases.entity.cheatmeal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "record", schema = "cheat_meal")
@Data
public class Record {
  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 客戶 ID */
  @Column(name = "user_id")
  private String userId;

  /** 作弊餐紀錄開始日期 */
  @Column(name = "start_at")
  private LocalDate startAt;

  /** 作弊餐紀錄結束日期 */
  @Column(name = "end_at")
  private LocalDate endAt;

  /** 使用者作弊餐總額度 */
  private Integer budget;
}

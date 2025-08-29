package com.robbie.personaltools.infra.databases.entity.cheatmeal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "consumption_record", schema = "cheat_meal")
@Data
public class ConsumptionRecord {
  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "budget")
  private Integer budget;

  @Column(name = "week_start")
  private LocalDate weekStart;

  @Column(name = "week_end")
  private LocalDate weekEnd;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
}

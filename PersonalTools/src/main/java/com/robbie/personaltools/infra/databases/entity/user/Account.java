package com.robbie.personaltools.infra.databases.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "account", schema = "user")
@Data
public class Account {
  /** 會員編號 */
  @Id private String id;

  /** 會員類型 */
  @Column(name = "user_type")
  private String userType;

  /** 會員名稱 */
  @Column(name = "name")
  private String name;

  /** google 登入 id */
  @Column(name = "google_id")
  private String googleId;

  /** google 登入 email */
  @Column(name = "email")
  private String email;

  /** google 大頭貼 */
  @Column(name = "image_url")
  private String imageUrl;

  /** 建立時間 */
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  /** 更新時間 */
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "budget")
  private Integer budget;
}

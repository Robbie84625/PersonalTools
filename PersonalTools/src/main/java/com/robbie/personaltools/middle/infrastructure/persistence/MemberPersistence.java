package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.MemberDao;
import com.robbie.personaltools.infra.databases.entity.user.Account;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistence {
  private final MemberDao memberDao;

  public Optional<Account> findByUserId(String userId) {
    return this.memberDao.findById(userId);
  }
}

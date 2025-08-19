package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.user.AccountDao;
import com.robbie.personaltools.infra.databases.entity.user.Account;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountPersistence {
  private final AccountDao accountDao;

  public Optional<Account> findByUserId(String userId) {
    return this.accountDao.findById(userId);
  }

  public void saveAccount(Account account) {
    this.accountDao.save(account);
  }
}

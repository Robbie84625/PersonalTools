package com.robbie.infra.databases.dao.user;

import com.robbie.infra.databases.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, String> {}

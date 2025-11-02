package com.robbie.backend.infra.databases.dao.user;

import com.robbie.backend.infra.databases.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, String> {}

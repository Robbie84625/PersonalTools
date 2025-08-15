package com.robbie.personaltools.infra.databases.dao.cheatmeal;

import com.robbie.personaltools.infra.databases.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Account, String> {}

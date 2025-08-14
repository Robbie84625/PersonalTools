package com.robbie.personaltools.middle.infrastructure.persistence;

import com.robbie.personaltools.infra.databases.dao.cheatmeal.MemberDao;
import com.robbie.personaltools.infra.databases.entity.member.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistence {
  private final MemberDao memberDao;

  public Optional<Member> findByCustomerId(String customerId) {
    return this.memberDao.findById(customerId);
  }
}

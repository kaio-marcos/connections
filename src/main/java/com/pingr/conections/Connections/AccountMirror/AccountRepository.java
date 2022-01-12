package com.pingr.conections.Connections.AccountMirror;

import com.pingr.conections.Connections.AccountMirror.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}

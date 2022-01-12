package com.pingr.conections.Config;

import com.pingr.conections.Connections.AccountMirror.Account;
import com.pingr.conections.Connections.AccountMirror.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

// roda previamente essas configurações
//@Configuration
//public class PrePopulateAccountConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(AccountRepository repo) {
//        return args -> {
//            Account a1 = new Account(1L, "primeira", new HashSet<>());
//            Account a2 = new Account(2L, "seegunda", new HashSet<>());
//            Account a3 = new Account(3L, "terceira", new HashSet<>());
//            Account a4 = new Account(4L, "quarta", new HashSet<>());
//            Account a5 = new Account(5L, "quinta", new HashSet<>());
//
//            repo.saveAll(Arrays.asList(a1, a2, a3, a4, a5));
//        };
//    }
//}

//package com.tapi.trackerapi.User;
//
//import com.tapi.trackerapi.EXPENSE.model.User;
//import com.tapi.trackerapi.EXPENSE.repository.UserRepo;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//public class UserRepoTest {
//    @Autowired
//    private UserRepo repo;
//
//    @Test
//    public void testCreateUser() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = passwordEncoder.encode("nam2020");
//
//        User newUser = new User("nam@codejava.net", password);
//        User savedUser = repo.save(newUser);
//
//        assertThat(savedUser).isNotNull();
//        assertThat(savedUser.getUid()).isGreaterThan(0);
//    }
//}

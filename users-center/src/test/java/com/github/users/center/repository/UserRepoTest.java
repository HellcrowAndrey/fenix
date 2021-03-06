package com.github.users.center.repository;

import com.github.users.center.entity.Role;
import com.github.users.center.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.github.users.center.repository.RepositoryMocks.user;
import static com.github.users.center.repository.RepositoryMocks.userExp;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void createUser() {
        User user = user();
        User exp = userExp();
        User act = this.userRepo.save(user);
        assertNotNull(act);
        Assertions.assertThat(act)
                .usingRecursiveComparison()
                .ignoringFields("id", "createAt", "updateAt", "roles")
                .isEqualTo(exp);
        Assertions.assertThat(act.getRoles())
                .usingElementComparatorIgnoringFields("createAt", "updateAt")
                .contains(exp.getRoles().toArray(new Role[]{}));
    }

    @Test
    public void findById() {
        User user = user();
        User exp = userExp();
        User data = this.userRepo.save(user);
        User act = this.userRepo.findById(data.getId())
                .orElse(null);
        assertNotNull(act);
        Assertions.assertThat(act)
                .usingRecursiveComparison()
                .ignoringFields("id", "createAt", "updateAt", "roles")
                .isEqualTo(exp);
        Assertions.assertThat(act.getRoles())
                .usingElementComparatorIgnoringFields("createAt", "updateAt")
                .contains(exp.getRoles().toArray(new Role[]{}));
    }

    @Test
    public void findByEmail() {
        User user = user();
        User exp = userExp();
        User data = this.userRepo.save(user);
        User act = this.userRepo.findByEmail(data.getEmail())
                .orElse(null);
        assertNotNull(act);
        Assertions.assertThat(act)
                .usingRecursiveComparison()
                .ignoringFields("id", "createAt", "updateAt", "roles")
                .isEqualTo(exp);
        Assertions.assertThat(act.getRoles())
                .usingElementComparatorIgnoringFields("createAt", "updateAt")
                .contains(exp.getRoles().toArray(new Role[]{}));
    }

    @Test
    public void findByLogin() {
        User user = user();
        User exp = userExp();
        User data = this.userRepo.save(user);
        User act = this.userRepo.findByLogin(data.getLogin())
                .orElse(null);
        assertNotNull(act);
        Assertions.assertThat(act)
                .usingRecursiveComparison()
                .ignoringFields("id", "createAt", "updateAt", "roles")
                .isEqualTo(exp);
        Assertions.assertThat(act.getRoles())
                .usingElementComparatorIgnoringFields("createAt", "updateAt")
                .contains(exp.getRoles().toArray(new Role[]{}));
    }

    @Test
    public void findByEmailOrLogin() {
        User user = user();
        User exp = userExp();
        User data = this.userRepo.save(user);
        User act = this.userRepo.findByEmailOrLogin(
                data.getEmail(), data.getLogin()
        ).orElse(null);
        assertNotNull(act);
        Assertions.assertThat(act)
                .usingRecursiveComparison()
                .ignoringFields("id", "createAt", "updateAt", "roles")
                .isEqualTo(exp);
        Assertions.assertThat(act.getRoles())
                .usingElementComparatorIgnoringFields("createAt", "updateAt")
                .contains(exp.getRoles().toArray(new Role[]{}));
    }

    @Test
    public void existsByEmailOrLoginTrue() {
        User user = user();
        User data = this.userRepo.save(user);
        boolean act = this.userRepo.existsByEmailOrLogin(
                data.getEmail(), data.getLogin()
        );
        assertTrue(act);
    }

    @Test
    public void existsByEmailOrLoginFalse() {
        User user = user();
        boolean act = this.userRepo.existsByEmailOrLogin(
                user.getEmail(), user.getLogin()
        );
        assertFalse(act);
    }

}
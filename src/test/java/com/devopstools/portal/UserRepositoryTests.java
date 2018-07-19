package com.devopstools.portal;

import com.devopstools.portal.integration.database.entity.User;
import com.devopstools.portal.integration.database.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        User alex = new User("Alex");
        entityManager.persist(alex);
        entityManager.flush();

        List<User> found = userRepository.findByName(alex.getName());

        assertThat(found.get(0).getName())
                .isEqualTo(alex.getName());
    }
}

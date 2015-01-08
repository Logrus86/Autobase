package com.epam.bp.autobase;

import com.epam.bp.autobase.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.*;
import java.math.BigDecimal;

public class Runner {

    public static void main(String[] args) {

        User user = new User()
                .setFirstname("Blabla")
                .setLastname("Blablabla")
                .setEmail("mail12@mail.com")
                .setUsername("USER_TEST2")
                .setPassword("111")
                .setBalance(new BigDecimal(10000))
                .setRole(User.Role.ADMIN)
                .setDob("1986-09-15");

            EntityManager em = Persistence.createEntityManagerFactory("RUNNER").createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(user);
            et.commit();
            em.close();

    }
}

package com.epam.bp.autobase;

import com.epam.bp.autobase.entity.Identifiable;
import com.epam.bp.autobase.entity.User;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Random;

public class Runner {

    private static User randomUser() {
        Integer i = new Random().nextInt(1000);
        return new User()
                .setFirstname("Blabla")
                .setLastname("Blablabla")
                .setEmail("mail"+String.valueOf(i)+"@mail.com")
                .setUsername("USER_TEST"+String.valueOf(i))
                .setPassword("111")
                .setBalance(new BigDecimal(i*100))
                .setRole(User.Role.CLIENT)
                .setDob("1986-09-15");
    }
    private static void addEntity(Identifiable entity) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RUNNER");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
        emf.close();
    }
    private static Object getEntity(Class entityClass, Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RUNNER");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Object entity = em.find(entityClass, id);
        et.commit();
        em.close();
        emf.close();
        return entity;
    }

    public static void deleteEntity(Class entityClass, Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RUNNER");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.find(entityClass, id));
        et.commit();
        em.close();
        emf.close();
    }

    public static void main(String[] args) {
        User user = randomUser();

        addEntity(user);
        System.out.println(getEntity(User.class, user.getId()));
      //  deleteEntity(User.class, user.getId());
    }
}

package com.mycompany.mavenproject1.repository;

import com.mycompany.mavenproject1.model.User;
//import com.example.jakarta_project.repository.dt.DatabaseConnection;
//import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public class UserRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

//    public UserRepository() {
//        entityManager = new DatabaseConnection().getEntityManager();
//    }

    public boolean validateUser(String username, String password){
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        return user != null;
    }

//    private String passwordEncode(String password) {
//
//    }


}

package com.mycompany.mavenproject1.repository;

import com.mycompany.mavenproject1.model.Game;
//import com.example.jakarta_project.repository.dt.DatabaseConnection;
//import com.example.jakarta_project.repository.dt.EntityManagerProducer;
//import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;


public class GameRepository {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

//    public GameRepository() {
//
//        entityManager = new DatabaseConnection().getEntityManager();
//    }

    public void save(Game game){
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        entityManager.persist(game);
        //transaction.commit();
    }

    public Game findById(Long id){
        return entityManager.find(Game.class, id);
    }

    public void update(Game game){
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        entityManager.merge(game);
        //transaction.commit();
    }

    public void delete(Long id){
        Game game = findById(id);
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        entityManager.remove(game);
        //transaction.commit();
    }

    public List<Game> findAll(){
        return entityManager.createQuery("SELECT g FROM Game g", Game.class).getResultList();
    }

    public Game findByDate(Date date) {
        return entityManager.createQuery("SELECT g FROM Game g WHERE g.date = :date", Game.class).setParameter("date", date).getSingleResult();
    }
}

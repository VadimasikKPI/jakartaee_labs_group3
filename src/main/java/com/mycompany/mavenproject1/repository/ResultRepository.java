package com.mycompany.mavenproject1.repository;

import com.mycompany.mavenproject1.model.Results;
import com.mycompany.mavenproject1.model.Team;
//import com.example.jakarta_project.repository.dt.DatabaseConnection;
//import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

import java.util.List;


public class ResultRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

//    public ResultRepository() {
//        entityManager = new DatabaseConnection().getEntityManager();
//    }

    public void save(Results result){
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        entityManager.persist(result);
        //transaction.commit();
    }

    public Results findById(Long id){
        return entityManager.find(Results.class, id);
    }

    public void update(Results result){
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        entityManager.merge(result);
        //transaction.commit();
    }

    public void delete(Long id){
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        Results result = findById(id);
        entityManager.remove(result);
        //transaction.commit();
    }

    public List<Results> findAll(){
        return entityManager.createQuery("SELECT r FROM Results r", Results.class).getResultList();
    }

    public Results findByWinner(Team winnerName) {
        return entityManager.createQuery("SELECT r FROM Results r WHERE r.winnerName = :winnerName", Results.class).setParameter("winnerName", winnerName).getSingleResult();
    }
}

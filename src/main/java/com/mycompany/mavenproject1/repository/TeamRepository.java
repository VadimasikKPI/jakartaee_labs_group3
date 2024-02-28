package com.mycompany.mavenproject1.repository;

import com.mycompany.mavenproject1.model.Team;
//import com.example.jakarta_project.repository.dt.DatabaseConnection;
import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.util.List;

@ApplicationScoped
public class TeamRepository{
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

//    public TeamRepository() {
//        entityManager = new DatabaseConnection().getEntityManager();
//    }

    public void save(Team team){
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        entityManager.persist(team);
        //transaction.commit();
    }

    public Team findById(Long id){
        return entityManager.find(Team.class, id);
    }

    public void update(Team team){
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        entityManager.merge(team);
        //transaction.commit();
    }

    public void delete(Long id){
        Team team = findById(id);
        //System.out.println("TeamRepository.delete");
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        entityManager.remove(team);
        //transaction.commit();
    }

    public List<Team> findAll(){
        return entityManager.createQuery("SELECT t FROM Team t", Team.class).getResultList();
    }

    public Team findByName(String teamName) {
        return entityManager.createQuery("SELECT t FROM Team t WHERE t.teamName = :teamName", Team.class)
                .setParameter("teamName", teamName)
                .getSingleResult();
    }

    public List<Team> getTeamsFilteredByName(String teamName) {
        return entityManager.createQuery("SELECT t FROM Team t WHERE t.teamName LIKE :teamName", Team.class)
                .setParameter("teamName", "%" + teamName + "%")
                .getResultList();
    }
}

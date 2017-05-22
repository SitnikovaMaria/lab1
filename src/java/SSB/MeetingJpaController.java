/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSB;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Meeting;

/**
 *
 * @author admin
 */
public class MeetingJpaController {
    public EntityManager em = Persistence.createEntityManagerFactory("nclab3PU").createEntityManager();
    
    public Meeting get(long id){
        return em.find(Meeting.class, id);
    }
    public List<Meeting> getAll(){
      String ejbQL = "SELECT m FROM Meeting m";
      Query query = em.createQuery(ejbQL); 
      return (List) query.getResultList();
    }
    
    public void add(Meeting meeting){
        em.getTransaction().begin();
        em.persist(meeting);
        em.getTransaction().commit();
    }
 
    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }
    
}


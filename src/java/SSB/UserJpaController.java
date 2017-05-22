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
import model.User;

/**
 *
 * @author admin
 */
public class UserJpaController {
    public EntityManager em = Persistence.createEntityManagerFactory("nclab3PU").createEntityManager();
    public User get(long id){
        return em.find(User.class, id);
    }
    public List<User> getAll(){
      String ejbQL = "SELECT u FROM User u";
      Query query = em.createQuery(ejbQL); 
      return (List) query.getResultList();
    }
    
    public void add(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
 
    public void delete(long id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }
}

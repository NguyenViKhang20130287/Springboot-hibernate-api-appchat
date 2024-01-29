package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Dao.UserDao;
import com.spring.hibernate.api_appchat.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public User findByEmail(String email) {
        String query = "from User where email=:emailInput";
        TypedQuery<User> typedQuery_user = entityManager.createQuery(query, User.class);
        typedQuery_user.setParameter("emailInput", email);
        return typedQuery_user.getResultList().isEmpty() ? null : typedQuery_user.getSingleResult();
    }
}

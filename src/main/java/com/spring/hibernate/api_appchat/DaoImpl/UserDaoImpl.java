package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Dao.UserDao;
import com.spring.hibernate.api_appchat.Dto.UserDto;
import com.spring.hibernate.api_appchat.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private EntityManager entityManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDaoImpl(EntityManager entityManager,PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public ResponseEntity<?>  findByEmail(String email) {
        String query = "from User where email=:emailInput";
        TypedQuery<User> typedQuery_user = entityManager.createQuery(query, User.class);
        typedQuery_user.setParameter("emailInput", email);
        if (typedQuery_user.getResultList().isEmpty()) return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(typedQuery_user.getSingleResult(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> editUser(UserDto userDto) {
        User user = entityManager.find(User.class, userDto.getId());
        if (user == null) return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setDisplayName(userDto.getDisplayName());
        user.setAvatar(userDto.getAvatar());
        entityManager.merge(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

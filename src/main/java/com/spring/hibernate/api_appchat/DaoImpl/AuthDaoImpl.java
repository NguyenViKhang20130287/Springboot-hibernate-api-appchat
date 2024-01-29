package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Config.EmailConfig;
import com.spring.hibernate.api_appchat.Config.OTPConfig;
import com.spring.hibernate.api_appchat.Dao.AuthDao;
import jakarta.persistence.EntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDaoImpl implements AuthDao {
    private EntityManager entityManager;
    private PasswordEncoder passwordEncoder;
    private EmailConfig emailConfig;
    private OTPConfig otpConfig;

    public AuthDaoImpl(EntityManager entityManager, PasswordEncoder passwordEncoder,
                       EmailConfig emailConfig, OTPConfig otpConfig) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
        this.emailConfig = emailConfig;
        this.otpConfig = otpConfig;
    }
}

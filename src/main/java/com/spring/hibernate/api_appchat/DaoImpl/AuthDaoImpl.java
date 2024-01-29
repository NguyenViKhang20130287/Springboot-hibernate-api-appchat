package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Config.EmailConfig;
import com.spring.hibernate.api_appchat.Config.OTPConfig;
import com.spring.hibernate.api_appchat.Dao.AuthDao;
import com.spring.hibernate.api_appchat.Dao.UserDao;
import com.spring.hibernate.api_appchat.Dto.AuthDto;
import com.spring.hibernate.api_appchat.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthDaoImpl implements AuthDao {
    private EntityManager entityManager;
    private PasswordEncoder passwordEncoder;
    private EmailConfig emailConfig;
    private OTPConfig otpConfig;
    private UserDao userDao;
    private final Map<String, String> mapOTP = new HashMap<>();

    public AuthDaoImpl(EntityManager entityManager, PasswordEncoder passwordEncoder,
                       EmailConfig emailConfig, OTPConfig otpConfig,
                       UserDao userDao) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
        this.emailConfig = emailConfig;
        this.otpConfig = otpConfig;
        this.userDao = userDao;
    }

    @Override
    public ResponseEntity<?> signUp(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) return new ResponseEntity<>("Email already exist !!!", HttpStatus.BAD_REQUEST);
        otpConfig.clearOtp(mapOTP, email);
        String otp = otpConfig.generateOtp(mapOTP, email);
        emailConfig.send("SIGN UP", email, otp);
        otpConfig.setTimeOutOtp(mapOTP, email, 3);
        return new ResponseEntity<>("OTP is sent. The OTP is only valid for 3 minutes!", HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> confirmSignUp(AuthDto authDto) {
        if (!otpConfig.checkEmailIsValid(mapOTP, authDto.getEmail()))
            return new ResponseEntity<>("OTP has expired!!!", HttpStatus.BAD_REQUEST);
        if (!mapOTP.get(authDto.getEmail()).equals(authDto.getOtp()))
            return new ResponseEntity<>("OTP invalid!!!", HttpStatus.BAD_REQUEST);
        User user = new User();
        user.setEmail(authDto.getEmail());
        user.setPassword(passwordEncoder.encode(authDto.getPassword()));
        user.setDisplayName(authDto.getDisplayName());
        user.setAvatar("Avatar link");
        user.setIsAdmin(1);
        entityManager.persist(user);
        otpConfig.clearOtp(mapOTP, authDto.getEmail());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> signIn(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user == null) return new ResponseEntity<>("Email not found!!!", HttpStatus.NOT_FOUND);
        if (!passwordEncoder.matches(password, user.getPassword()))
            return new ResponseEntity<>("Password invalid!!", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> forgotPassword(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) return new ResponseEntity<>("Email not found!!!", HttpStatus.NOT_FOUND);
        System.out.println("Map otp: " + mapOTP);
        otpConfig.clearOtp(mapOTP, email);
        String otp = otpConfig.generateOtp(mapOTP, email);
        emailConfig.send("RESET PASSWORD", email, otp);
        otpConfig.setTimeOutOtp(mapOTP, email, 3);
        return new ResponseEntity<>("OTP is sent. The OTP is only valid for 3 minutes!", HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> resetPassword(AuthDto authDto) {
        User user = userDao.findByEmail(authDto.getEmail());
        if (user == null) return new ResponseEntity<>("User not found!!!", HttpStatus.BAD_REQUEST);
        if (!otpConfig.checkEmailIsValid(mapOTP, authDto.getEmail())) {
            return new ResponseEntity<>("OTP has expired!!!", HttpStatus.BAD_REQUEST);
        }
        if (!mapOTP.get(authDto.getEmail()).equals(authDto.getOtp())) {
            return new ResponseEntity<>("OTP invalid!!!", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(authDto.getNewPassword()));
        entityManager.merge(user);
        otpConfig.clearOtp(mapOTP, authDto.getEmail());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

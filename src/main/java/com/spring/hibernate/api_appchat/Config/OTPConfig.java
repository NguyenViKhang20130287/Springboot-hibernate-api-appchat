package com.spring.hibernate.api_appchat.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class OTPConfig {
    public String generateOtp(Map<String, String> mapOtp, String email) {
        String otp = String.format("%04d", (int) (Math.random() * 1000000));
        mapOtp.put(email, otp);
        return otp;
    }

    public boolean checkEmailIsValid(Map<String, String> mapOtp, String email) {
        return mapOtp.containsKey(email);
    }

    public void setTimeOutOtp(Map<String, String> mapOtp, String email, int minutes) {
        new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(minutes);
                mapOtp.remove(email);
                System.out.println("3 Minutes finish. OTP is clear.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void clearOtp(Map<String, String> mapOtp, String email) {
        mapOtp.remove(email);
        System.out.println("OTP email: " + email + " is clear!!!");
    }
}

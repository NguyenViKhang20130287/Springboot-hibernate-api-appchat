package com.spring.hibernate.api_appchat.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class OTPConfig {
    private final Map<String, String> mapOtp;

    @Autowired
    public OTPConfig(Map<String, String> mapOtp){
        this.mapOtp = mapOtp;
    }

    public String generateOtp(String email) {
        String otp = String.format("%04d", (int) (Math.random() * 1000000));
        mapOtp.put(email, otp);
        return otp;
    }

    private boolean checkEmailIsValid(String email) {
        return mapOtp.containsKey(email);
    }

    private void setTimeOutOtp(String email, int minutes) {
        new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(minutes);
                mapOtp.remove(email);
                System.out.println("3 Minutes finish.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void clearOtp(String email) {
        mapOtp.remove(email);
        System.out.println("OTP email: " + email + " is clear!!!");
    }
}

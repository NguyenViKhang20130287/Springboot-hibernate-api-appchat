package com.spring.hibernate.api_appchat.ServiceImpl;

import com.spring.hibernate.api_appchat.Dao.PrivateMessageDao;
import com.spring.hibernate.api_appchat.DaoImpl.PrivateMessageDaoImpl;
import com.spring.hibernate.api_appchat.Dto.PrivateMessageDto;
import com.spring.hibernate.api_appchat.Service.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {
    private PrivateMessageDao privateMessageDao;

    @Autowired
    public PrivateMessageServiceImpl(PrivateMessageDao privateMessageDao){
        this.privateMessageDao = privateMessageDao;
    }
    @Override
    public ResponseEntity<?> sendMessage(PrivateMessageDto privateMessageDto) {
        return privateMessageDao.sendMessage(privateMessageDto);
    }
}

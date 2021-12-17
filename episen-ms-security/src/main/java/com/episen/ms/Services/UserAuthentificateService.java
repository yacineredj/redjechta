package com.episen.ms.Services;

import com.episen.ms.model.UserAuthentificate;
import com.episen.ms.repository.UserAuthentificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserAuthentificateService {

    @Autowired
    private UserAuthentificateRepository userAuthentificateRepository ;


    public UserAuthentificate getUser (UserAuthentificate userAuthentificate){
        UserAuthentificate userDataFound = userAuthentificateRepository.findByUserauthAndPassword(userAuthentificate.getUserAuth(),userAuthentificate.getPassword());
        return userDataFound;
    }


    public UserAuthentificate getByUserAuth(String userAuth) {
        UserAuthentificate userDataFound = userAuthentificateRepository.findByUserauth(userAuth);
        return userDataFound;
    }


    public void registerUserAuth(UserAuthentificate userAuthentificate) {
        userAuthentificateRepository.save(userAuthentificate);
    }



}


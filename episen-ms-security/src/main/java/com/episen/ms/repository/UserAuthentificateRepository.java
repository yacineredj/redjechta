package com.episen.ms.repository;


import com.episen.ms.model.UserAuthentificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserAuthentificateRepository extends JpaRepository<UserAuthentificate, Long> {

    UserAuthentificate findByUserauth(String userAuth);
    UserAuthentificate findByUserauthAndPassword(String userAuth,String password);


}


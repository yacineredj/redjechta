package com.episen.ms.Controller;

import com.episen.ms.Services.UserAuthentificateService;
import com.episen.ms.model.UserAuthentificate;
import com.episen.ms.model.UserContext;
import com.episen.ms.security.JwTokenGenerator;
import com.episen.ms.security.JwTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class userAuthentificateController {

    @Autowired
    private JwTokenValidator jwtValidator;
    @Autowired
    private JwTokenGenerator jwtGenerator;
    @Autowired
    private UserAuthentificateService userAuthentificateService;

    @PostMapping(value = "/authentification")
    public String auth(@RequestBody UserAuthentificate userAuthentificate){

        if(userAuthentificateService.getUser(userAuthentificate)!=null){
            String token = jwtGenerator.generateToken(userAuthentificate.getUserAuth(), Arrays.asList("ADMIN"));
            return token;

        }
        return "Ce User n'existe pas";
    }

    @PostMapping("/checkuser")
    public UserContext parseToken(@RequestBody UserContext value){
        return jwtValidator.transforme(value.getSubject());
    }
}

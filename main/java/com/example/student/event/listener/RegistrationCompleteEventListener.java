package com.example.student.event.listener;

import com.example.student.User;
import com.example.student.UserService;
import com.example.student.event.RegistrationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;



    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create verification  token with url for the user
        User user= event.getUser();
        String token= UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser( token,user);
        String url=event.getApplicationUrl()+"verifyRegistration?token"+
                token;
        // send verification email over here
        log.info("click on the link to verify your account ",url);

        //send mail to user
    }
}

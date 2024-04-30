package com.techacademy.beans;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.techacademy.dtos.LoginForm;
import com.techacademy.entities.User;
import com.techacademy.repositories.UserRepository;

@Component
@SessionScope
public class SessionControl {
    @Autowired
    private UserRepository userRepository;
    private User user;
    private Logger logger = LoggerFactory.getLogger(SessionControl.class);

    
    public boolean login(LoginForm loginForm) {
        this.user = this.userRepository.findByEmailAndPassword(loginForm.getMailAddress(), loginForm.getPassword());
        if (user == null) {
            return false;
        } else {
            this.logger.info("log in(" + this.user + ")");
            return true;
        }
        
    }
    
    public void logout() {
        this.user = null;
    }
    
    public boolean isLogin() {
        if(user == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public User getUser() {
        return this.user;
    }
    
}

package com.jmcaskey.auth.service;

import com.jmcaskey.auth.model.Theme;
import com.jmcaskey.auth.model.User;

public interface UserService {
    void save(User user);
    
    void save(Theme theme);
    
    User findByUsername(String username);
    
    Theme findThemeByUsername(String username);
    
    void update(String theme, String username);
}

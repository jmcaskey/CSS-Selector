package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Theme;
import com.hellokoding.auth.model.User;

public interface UserService {
    void save(User user);
    
    void save(Theme theme);
    
    User findByUsername(String username);
    
    Theme findThemeByUsername(String username);
    
    void update(String theme, String username);
}

package com.jmcaskey.auth.service;

import com.jmcaskey.auth.model.Theme;
import com.jmcaskey.auth.model.User;
import com.jmcaskey.auth.repository.RoleRepository;
import com.jmcaskey.auth.repository.ThemeRepository;
import com.jmcaskey.auth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }
    
    @Override
    public void save(Theme theme) {
    	themeRepository.save(theme);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public Theme findThemeByUsername(String username) {
        return themeRepository.findByUsername(username);
    }
    
    @Override
    public void update(String theme, String username) {
    	themeRepository.setThemeByUsername(theme, username);
    }
}

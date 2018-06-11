package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Theme;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findByUsername(String username);
    
    @Transactional
    @Modifying
    @Query("update Theme t set t.theme = ?1 where t.username = ?2")
    void setThemeByUsername(String theme, String username);
}
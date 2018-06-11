package com.jmcaskey.auth.model;

import javax.persistence.*;

@Entity
@Table(name = "theme")
public class Theme {
    private Long id;
    private String username;
    private String theme;
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getTheme() {
    	return theme;
    }
    public void setTheme(String theme) {
    	this.theme = theme;
    }
}

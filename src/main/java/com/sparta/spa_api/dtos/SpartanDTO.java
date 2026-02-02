package com.sparta.spa_api.dtos;

import jakarta.validation.constraints.NotNull;

public class SpartanDTO {

    private int id;
    @NotNull

    private String username;
    @NotNull

    private String role;
    @NotNull

    private String password;

    // Constructors
    public SpartanDTO() {
    }

    public SpartanDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}

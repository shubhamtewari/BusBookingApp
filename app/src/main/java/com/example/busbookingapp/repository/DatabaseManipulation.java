package com.example.busbookingapp.repository;

public interface DatabaseManipulation {
    public boolean signInUser(String name, String password) throws Exception;
}

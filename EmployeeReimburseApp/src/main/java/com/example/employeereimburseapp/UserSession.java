package com.example.employeereimburseapp;

public class UserSession {

    private static UserSession user;
    private int id;
    private String name;
    private String email;
    private String role;

    private UserSession(int id, String name, String email, String role) {
        user.id = id;
        user.name = name;
        user.email = email;
        user.role = role;
    }

    public static void createUser(int id, String name, String email, String role) {
        user = new UserSession(id, name, email, role);
    }

    public static UserSession getUser() {
        if (user != null) {
            return user;
        }else  {
            return null;
        }
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getRole() {return role;}

}

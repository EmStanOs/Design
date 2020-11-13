package org.os.GraduationProject.pojo;

public class User {
    private int id;
    private String user;
    private String password;
    private String power;

    public User() {
    }

    public User(int id, String user, String password,String power) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.power = power;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

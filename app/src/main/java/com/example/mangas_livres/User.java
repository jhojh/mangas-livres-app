package com.example.mangas_livres;

public class User {
    int id;
    String name;
    String email;
    String password;
    String photo;
    int catguser_id;
    private boolean success;
    private String message;

    public User(int id, String name, String email, String password, String photo, int catguser_id, boolean success, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.catguser_id = catguser_id;
        this.success = success;
        this.message = message;
    }
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCatguser_id() {
        return catguser_id;
    }

    public void setCatguser_id(int catguser_id) {
        this.catguser_id = catguser_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

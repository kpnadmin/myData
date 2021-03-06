package com.android.mylibrary;

public class ModelPerson {
    
    private String id      = ""; 
    private String pw      = "";
    private String name    = ""; 
    private String email   = "";
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
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
    
    @Override
    public String toString() {
        return "ModelPerson [id=" + id + ", pw=" + pw + ", name=" + name
                + ", email=" + email + "]";
    }
    
    public ModelPerson() {
        super();
    }
    public ModelPerson(String id, String pw) {
        super();
        this.id = id;
        this.pw = pw;
    }
    public ModelPerson(String id, String pw, String name, String email) {
        super();
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
    } 
    
    
    
}

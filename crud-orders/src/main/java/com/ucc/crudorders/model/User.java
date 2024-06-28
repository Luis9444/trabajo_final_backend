package com.ucc.crudorders.model;

import java.io.Serializable;

public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  private String user;
  private String password;

  public User(String user, String password) {
    this.user = user;
    this.password = password;
  }

  public String getuser() {
    return user;
  }

  public void setuser(String username) {
    this.user = username;
  }

  public String getpassword() {
    return password;
  }

  public void setpassword(String password) {
    this.password = password;
  }
}

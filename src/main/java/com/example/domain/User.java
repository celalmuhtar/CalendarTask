package com.example.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "USERS")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "USERID")
   private Long              userId;

   @Column(name = "USERNAME", nullable = false, unique = true)
   private String            username;

   @Column(name = "PASSWORD", nullable = false)
   private String            password;

   @Column(name = "EMAIL", nullable = false, unique = true)
   private String            email;

   @Column(name = "ENABLED", nullable = false)
   private int               enabled;

   @Column(name = "NAME", nullable = false)
   private String            name;

   @Column(name = "SURENAME", nullable = false)
   private String            surename;

   public User() {
   }

   public User(User user) {
      this.userId = user.userId;
      this.username = user.username;
      this.email = user.email;
      this.password = user.password;
      this.enabled = user.enabled;
      this.name = user.name;
      this.surename = user.surename;
   }

   public User(Long userId, String username, String password, String email, int enabled, String name, String surename) {
      super();
      this.userId = userId;
      this.username = username;
      this.password = password;
      this.email = email;
      this.enabled = enabled;
      this.name = name;
      this.surename = surename;
   }

   @Override
   public String toString() {
      return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
            + ", enabled=" + enabled + ", name=" + name + ", surename=" + surename + "]";
   }

}

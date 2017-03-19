package com.example.domain;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name="USER_ROLES")
public class UserRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="USER_ROLE_ID")
	private Long userroleid;
	
	@Column(name="USERID")
	private Long userid;
	
	@Column(name="ROLE")
	private String role;	
	
	public UserRole(){
	}

	@Override
	public String toString() {
		return "UserRole [userroleid=" + userroleid + ", userid=" + userid + ", role=" + role + "]";
	}

	public UserRole(Long userroleid, Long userid, String role) {
		super();
		this.userroleid = userroleid;
		this.userid = userid;
		this.role = role;
	}

	public UserRole(UserRole userRole){
		super();
		this.userroleid = userRole.userroleid;
		this.userid = userRole.userid;
		this.role = userRole.role;
	}
}

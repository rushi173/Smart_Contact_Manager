package com.scm.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "user")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String uId;

   
    private String uname;

   
    private String uemail;

    private String upassword;

    
    private String uabout;

  
    private String uprofilePic;

    private String uponeNo;

    private String ugender;

    private boolean enabled = true;

    private boolean emailVerified = false;

    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;

    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    private String emailToken;

    public User() {}

    public User(String uId, String uname, String uemail, String upassword, String uabout, String uprofilePic,
                String uponeNo, String ugender, boolean enabled, boolean emailVerified, boolean phoneVerified,
                Providers provider, String providerUserId, List<Contact> contacts, List<String> roleList, String emailToken) {
        this.uId = uId;
        this.uname = uname;
        this.uemail = uemail;
        this.upassword = upassword;
        this.uabout = uabout;
        this.uprofilePic = uprofilePic;
        this.uponeNo = uponeNo;
        this.ugender = ugender;
        this.enabled = enabled;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.contacts = contacts != null ? contacts : new ArrayList<>();
        this.roleList = roleList != null ? roleList : new ArrayList<>();
        this.emailToken = emailToken;
    }

    // Getters and Setters

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUabout() {
        return uabout;
    }

    public void setUabout(String uabout) {
        this.uabout = uabout;
    }

    public String getUprofilePic() {
        return uprofilePic;
    }

    public void setUprofilePic(String uprofilePic) {
        this.uprofilePic = uprofilePic;
    }

    public String getUponeNo() {
        return uponeNo;
    }

    public void setUponeNo(String uponeNo) {
        this.uponeNo = uponeNo;
    }

    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = true;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public Providers getProvider() {
        return provider;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }


    @Override
    public String getPassword() {
       return this.upassword;
    }

    @Override
    public String getUsername() {
        return this.uemail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled(){
        return this.enabled;
    }
   
}

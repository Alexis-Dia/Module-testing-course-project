package com.moduleTesting.rest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CurrentUserDetails implements UserDetails {
    private Long userID;
    private String emailAddress;
    private String password;
    private Role role;


    public CurrentUserDetails(Long userID, String emailAddress, String password, Role role) {
        super();
        this.userID = userID;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }


  /*    public static UserDetails create(Users entity) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for(Authorities auth: entity.getAuthorities()){
        authorities.add(new SimpleGrantedAuthority(auth.getId().getAuthority()));
    }
    return new MyUserDetail(entity.getUserId(), entity.getLoginId(), entity.getPassword(), entity.getDisplayName(), authorities);
}*/



    public Long getUserID(){
        return this.userID;
    }


    public Role getRole(){
        return this.role;
    }




    @Override
    public String getPassword() {
        return this.password;
    }


    public String getEmailAddress() {
        return this.emailAddress;
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
    public boolean isEnabled() {
        return true;
    }

    public static UserDetails create(User entity) {
        System.out.println(entity.getUserID()+ entity.getEmailAddress()+ entity.getPassword()+ entity.getRole());
        return new CurrentUserDetails(entity.getUserID(), entity.getEmailAddress(), entity.getPassword(), entity.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }
}

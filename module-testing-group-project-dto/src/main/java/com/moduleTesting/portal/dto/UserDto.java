package com.moduleTesting.portal.dto;

public class UserDto {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long userID;

    private String emailAddress;

    private String password;

    private Role role;

    public UserDto() {
        super();
    }

    public UserDto(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
            "userID=" + userID +
            ", emailAddress='" + emailAddress + '\'' +
            ", password='" + password + '\'' +
            ", role=" + role +
            '}';
    }
}
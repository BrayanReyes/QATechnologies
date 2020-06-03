package org.espnSuite.web.data;

public class UserDataESPN {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserDataESPN(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

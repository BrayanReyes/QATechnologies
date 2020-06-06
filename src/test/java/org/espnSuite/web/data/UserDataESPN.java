package org.espnSuite.web.data;

import org.espnSuite.web.utils.AccountStatus;

public class UserDataESPN {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AccountStatus statusAccount;

    public UserDataESPN(String firstName, String lastName, String email,
                        String password, AccountStatus statusAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.statusAccount=statusAccount;
    }

    public AccountStatus getStatusAccount() {
        return statusAccount;
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

    public static AccountStatus getAccountStatus(String accountStatus){
        for (AccountStatus status:AccountStatus.values()){
            if (status.toString().equals(accountStatus))
                return status;
        }
        return null;
    }

    @Override
    public String toString() {
        return "UserDataESPN{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", statusAccount=" + statusAccount +
                '}';
    }
}

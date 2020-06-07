package org.espnSuite.web.data;

import org.espnSuite.web.utils.AccountStatus;

/**
 * This class defines the parameters needed to handle user data 
 * @author: july.moreno
 * @version: 07/06/2020
 */


public class UserDataESPN {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AccountStatus statusAccount;

    /**
     * Constructor of UserDataESPN
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param statusAccount
     */
    public UserDataESPN(String firstName, String lastName, String email,
                        String password, AccountStatus statusAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.statusAccount=statusAccount;
    }

    /**
     * Get the status of an account
     * @return statusAccount
     */
    public AccountStatus getStatusAccount() {
        return statusAccount;
    }

    /**
     * Get user first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get user last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get user email address
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get user password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the status of an account
     * @param statusAccount
     */
    public void setStatusAccount(AccountStatus statusAccount) {
        this.statusAccount = statusAccount;
    }

    /**
     * Check if input parameter is in the enum list and return the corresponding value
     * @param accountStatus
     * @return
     */
    public static AccountStatus getAccountStatus(String accountStatus){
        for (AccountStatus status:AccountStatus.values()){
            if (status.toString().equals(accountStatus))
                return status;
        }
        return null;
    }

    /**
     * Concatenate all attributes of UserDataESPN into one String
     * @return String
     */
    @Override
    public String toString() {
        return "UserDataESPN: {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", statusAccount=" + statusAccount +
                '}';
    }
}

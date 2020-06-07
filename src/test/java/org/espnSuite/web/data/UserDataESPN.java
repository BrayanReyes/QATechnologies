package org.espnSuite.web.data;

import org.espnSuite.web.utils.AccountStatus;

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
     *
     * @return
     */
    public AccountStatus getStatusAccount() {
        return statusAccount;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
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
        return "UserDataESPN{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", statusAccount=" + statusAccount +
                '}';
    }
}

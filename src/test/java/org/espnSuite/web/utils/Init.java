package org.espnSuite.web.utils;

import org.espnSuite.web.data.UserDataESPN;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * This class defines the methods needed to handle User Accounts and use them through all
 * the test suite
 * @author: july.moreno
 * @version: 06/07/2020
 */

public class Init {

    /**
     * Read the  file UserDataESPN.dat in which the User Accounts info
     * @param status
     * @return arrayUserDataESPNs
     */
    public static Object[][] initUserDataESPNs(String status) {

        List<UserDataESPN> arrayUserDataESPNs = new ArrayList<>();

        try {

            File file = new File("src/test/java/org/espnSuite/web/utils/UserDataESPNs.dat");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(";");
                if (data[4].equals(status)) {
                    UserDataESPN user = new UserDataESPN(data[0], data[1], data[2], data[3], UserDataESPN.getAccountStatus(data[4]));
                    arrayUserDataESPNs.add(user);
                }
            }
            myReader.close();
        } catch (FileNotFoundException  e) {
            System.out.println("An error occurred reading UserDataESPNs file.");
            e.printStackTrace();
        }

        return convertListToArrayObject(arrayUserDataESPNs);
    }

    /**
     * Convert the arrayUserDataESPNs List into an Object
     * @param list
     * @return
     */
    public static Object[][] convertListToArrayObject(List<UserDataESPN> list){

        Object [][] objArray = new Object[list.size()][];
        for(int i=0;i< list.size();i++){
            objArray[i] = new Object[1];
            objArray[i][0] = list.get(i);
        }
        return objArray;
    }

    /**
     *Generate the user data needed to Create a new ESPN Account
     * @return new User Account Data
     */
    public static UserDataESPN createDataUser(){
        String firstName = generateRandomString();
;        String lastName = generateRandomString();
        String email = firstName+"."+lastName+"@espn.com";
        String password = firstName + "123" + "*" ;
        AccountStatus statusAccount = UserDataESPN.getAccountStatus("NEW");
        return new UserDataESPN(firstName,lastName,email,password,statusAccount);
    }

    /**
     * Random String generator to generate First and Last Name
     * @return
     */
    private static String generateRandomString(){
        int targetStringLength = 6;
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    /**
     * Set the status ACTIVE of the ESPN Account and save it
     * @param user
     */
    public static void saveUserLogOut(UserDataESPN user){
        user.setStatusAccount(AccountStatus.ACTIVE);
        saveUser(user);
    }

    /**
     * Set the status Deactivate of the ESPN Account and save it
     * @param user
     */
    public static void saveUserDeleteAccount(UserDataESPN user){
        user.setStatusAccount(AccountStatus.INACTIVE);
        saveUser(user);
    }

    /**
     * Save a new User Account Data into file
     * @param user
     */
    private static void saveUser(UserDataESPN user) {
        try {
            FileWriter file = new FileWriter("src/test/java/org/espnSuite/web/utils/UserDataESPNs.dat",true);
            file.write("\n"+user.getFirstName()+";"
                    +user.getLastName()+";"
                    +user.getEmail()+";"
                    +user.getPassword()+";" +
                    user.getStatusAccount().toString()+";" //After asserts are okay, we set it
            );
            file.close();
        } catch (Exception e) {
            System.out.println("An error occurred writing students file.");
            e.printStackTrace();
        }
    }
}

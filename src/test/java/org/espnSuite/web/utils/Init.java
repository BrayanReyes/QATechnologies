package org.espnSuite.web.utils;

import org.espnSuite.web.data.UserDataESPN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Init {


    public static UserDataESPN[] initUserDataESPNs() {

        List<UserDataESPN> arrayUserDataESPNs = new ArrayList<UserDataESPN>();

        try {

            File file = new File("src/test/java/org/espnSuite/web/utils/UserDataESPNs.dat");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(";");
                UserDataESPN user = new UserDataESPN(data[0],data[1],data[2],data[3],UserDataESPN.getAccountStatus(data[4]));
                arrayUserDataESPNs.add(user);
                System.out.println(user);
            }
            myReader.close();
        } catch (FileNotFoundException  e) {
            System.out.println("An error occurred reading UserDataESPNs file.");
            e.printStackTrace();
        }
        // Convert List to Array
        UserDataESPN[] users = new UserDataESPN[arrayUserDataESPNs.size()];
        arrayUserDataESPNs.toArray(users);
        return users;
    }

}

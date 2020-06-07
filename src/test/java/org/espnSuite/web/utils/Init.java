package org.espnSuite.web.utils;

import org.espnSuite.web.data.UserDataESPN;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Init {


    public static Object[][] initUserDataESPNs() {

        List<UserDataESPN> arrayUserDataESPNs = new ArrayList<UserDataESPN>();

        try {

            File file = new File("src/test/java/org/espnSuite/web/utils/UserDataESPNs.dat");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(";");
                UserDataESPN user = new UserDataESPN(data[0],data[1],data[2],data[3],UserDataESPN.getAccountStatus(data[4]));
                arrayUserDataESPNs.add(user);
            }
            myReader.close();
        } catch (FileNotFoundException  e) {
            System.out.println("An error occurred reading UserDataESPNs file.");
            e.printStackTrace();
        }

        return convertListToObject(arrayUserDataESPNs);
    }

    public static Object[][] convertListToObject(List<UserDataESPN> list){

        Object [][] objArray = new Object[list.size()][];
        for(int i=0;i< list.size();i++){
            objArray[i] = new Object[1];
            objArray[i][0] = list.get(i);
        }
        return objArray;
    }
}

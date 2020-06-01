package org.suiteTest.web.iframes.data;

import org.testng.annotations.DataProvider;

/**
 * Class with all Data Providers
 * @author Pinguin
 */
public class Data {

    /**
     *  Provides the list of names
     * @return data[][]
     */
    @DataProvider(name = "FlyReservation")
    public Object[][] inputNames(){
         return new Object[][] {
                 {new FlyDetail("LAS","",
                         "4","5", new String[] {"1", "2", "0","10","15"})},
                 {new FlyDetail("BOG","MED",
                         "6","2", new String[] {"1", "0"})},
                 {new FlyDetail("BOG","LAX",
                         "2","3", new String[] {"1", "2", "0"})},
                 {new FlyDetail("LAS","LAX",
                         "4","3", new String[] {"0", "2", "0"})},
                 {new FlyDetail("LAS","LAX",
                         "1","0", new String[] {})},
                 {new FlyDetail("LAS","LAX",
                         "7","0", new String[] {})},
                 {new FlyDetail("123","LAX",
                         "2","3", new String[] {"0", "2", "0"})},
                 {new FlyDetail("LAX","-a",
                         "2","1", new String[] {"a", "2", "-"})},
         };
     }
}
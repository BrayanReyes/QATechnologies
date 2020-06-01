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
                 {"LAS","LAX","2","1","0"}
                 ,
                 {"BOG","MED","6","1","0"}
         };
     }

}

package org.espnSuite.web.data;

import org.espnSuite.web.utils.Init;
import org.testng.annotations.DataProvider;

/**
 * This class defines the Data Providers that will be used for the test
 * @author: july.moreno
 * @version: 06/07/2020
 */

public class DataProviders {
    

	@DataProvider(name = "activeUsers")
    public static Object[][] activeUsersESPN(){
        return Init.initUserDataESPNs("ACTIVE");
    }
    
    @DataProvider(name = "newUsers")
    public static Object[][] newUsersESPN(){
        return Init.initUserDataESPNs("NEW");
    }

    @DataProvider(name = "inactiveUsers")
    public static Object[][] inactiveUsersESPN(){
        return Init.initUserDataESPNs("INACTIVE");
    }


}

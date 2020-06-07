package org.espnSuite.web.data;

import org.espnSuite.web.utils.Init;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "activeUsers")
    public static Object[][] activeUsersESPN(){
        return Init.initUserDataESPNs("ACTIVE");
    }

    @DataProvider(name = "newUsers")
    public static Object[][] newUsersESPN(){
        return Init.initUserDataESPNs("NEW");
    }


}

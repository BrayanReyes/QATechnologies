package org.espnSuite.web.data;

import org.espnSuite.web.utils.Init;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "Users")
    public Object[][] usersESPN(){
        return Init.initUserDataESPNs();
    }
}

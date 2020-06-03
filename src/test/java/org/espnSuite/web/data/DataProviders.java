package org.espnSuite.web.data;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "Users")
    public Object[][] usersESPN(){
        return new Object[][] { {new UserDataESPN("King",
                "s","qwaszx@espn.com",//"jmoreno@espn.com",
                "Espn2020+")} ,
//                {new UserDataESPN("July",
//                        "Moreno","qwaszx@espn.com",//"jmoreno@espn.com",
//                        "Espn2020+")} ,
        };
    }
}

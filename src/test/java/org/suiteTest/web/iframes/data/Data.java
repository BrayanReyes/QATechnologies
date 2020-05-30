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
    @DataProvider(name = "Names")
    public Object[][] inputNames(){
         return new Object[][] {{"Brian"},{"Pinguin"}};
     }

}

package com.automation.api.data;

import com.automation.api.pojo.Student;
import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider(name = "newStudent")
    public Object[][] inputNewStudent() {
        Student brian = new Student("Brian",
                "Kings",
                "brian.kings@globant.com",
                2.9,
                true);
        return new Object[][] {{brian}};
    }
 
    @DataProvider(name = "myStudent")
    public Object[][] inputMyStudent() {
        Student july = new Student("July",
                "Moreno",
                "july.moreno@globant.com",
                1.4,
                false);
        return new Object[][] {{july}};
    }
    
}

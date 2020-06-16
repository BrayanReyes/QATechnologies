package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.pojo.Student;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.api.steps.Students;


public class APIstudentsTest {

    private Students steps;

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        steps = new Students(uri);
    }

    @Test(description = "Get the list of students", enabled = false)
    public void getStudentsTest(){
        steps.getStudentsAPIEndpoint();
        steps.getStudents();
        steps.isStatusCode(200);
        steps.showActualStudentsList();
    }

    @Test(description = "Get an specific student", enabled = false)
    @Parameters({"name", "email"})
    public void getUserTest(String name, String email){
        steps.getStudentsAPIEndpoint();
        steps.getStudents();
        steps.isStatusCode(200);
        String id = steps.getStudentID(name);
        Assert.assertNotEquals(id, "", "User doesn't exists");
        steps.getStudent(id);
        steps.isStatusCode(200);
        steps.userEmailShouldBe(email);
    }

//    dataProviderClass = Data.class, dataProvider = "myStudent"
    @Test(description = "Update student final note", enabled = true)
    public void putFinalNoteTest(Student student){
        steps.getStudentsAPIEndpoint();
        steps.getStudents();
        steps.isStatusCode(200);
        String id = steps.getStudentID(student.getFirst_name() + " " + student.getLast_name());
        Assert.assertNotEquals(id, "", "User doesn't exists");
//        steps.getStudentsAPIEndpoint();
        steps.updateStudentFinalNote(id, 5.0);
        steps.isStatusCode(200);
        steps.finalNoteChanged(5.0);
    }

    @Test(description = "Update student approved status", dataProviderClass = Data.class, dataProvider = "myStudent", enabled = false)
    public void putApprovedStatusTest(Student student){
        steps.getStudentsAPIEndpoint();
        steps.getStudents();
        steps.isStatusCode(200);
        String id = steps.getStudentID(student.getFirst_name() + " " + student.getLast_name());
        Assert.assertNotEquals(id, "", "User doesn't exists");
//        steps.getStudentsAPIEndpoint();
        steps.updateStudentApprovedStatus(id, false);
        steps.isStatusCode(200);
        steps.finalApprovedStatus(false);
        steps.getStudent(id);
    }

}

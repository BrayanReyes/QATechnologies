package com.automation.api.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;

import com.automation.api.pojo.Student;
import com.vladsch.flexmark.internal.ParagraphParser;

import java.util.List;
import java.util.Optional;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class Students {

	private final String endpoint;
	private Response response;
	public Logger log = Logger.getLogger(Students.class);

	/**
	 * Constructor.
	 * 
	 * @param uri String
	 */
	public Students(String uri) {
		log.info("Getting the endpoint to test");
		endpoint = uri + "/v1/students/";
	}

	/**
	 * Get students endpoint print.
	 */
	@Step("Given the students API endpoint")
	public void getStudentsAPIEndpoint() {
		log.info("Given the students API endpoint");
		log.info(endpoint);
	}

	/**
	 * GET Method students/:id.
	 * 
	 * @param id String
	 */
	@Step("When I look for the student with id {0}")
	public void getStudent(String id) {
		log.info("Hitting GET Students Method by id");
		response = given().contentType(ContentType.JSON).when().get(endpoint + id);
		log.info(response.asString());
	}

	/**
	 * GET Method students (list of students).
	 */
	@Step("When I look for students")
	public void getStudents() {
		log.info("Hitting GET Method students");
		given().contentType(ContentType.JSON).when().get(endpoint);
	}

	/**
	 * PUT Method update final note of a student.
	 * 
	 * @param id         String
	 * @param final_note double
	 */
	@Step("When I update the final note of the student with id {0}")
	public void updateStudentFinalNote(String id, double newNote) {
		log.info("Hitting PUT students Method to update final_note");
		Student student = new Student();
		student.setFinal_note(newNote);
		
		
		given().contentType(ContentType.JSON).body(student).when().put(endpoint + id);
	}

	/**
	 * PUT Method update approved status of a student.
	 * 
	 * @param id       String
	 * @param approved boolean
	 */
	@Step("When I update the approved status of the student with id {0}")
	public void updateStudentApprovedStatus(String id, boolean approved) {
		log.info("Hitting PUT students Method to update approved status");

		Student student = new Student();
		student.setApproved(approved);

		given().contentType(ContentType.JSON).body(student).when().put(endpoint + id);
	}

	/**
	 * Verify expected final note.
	 * 
	 * @param final_note double
	 */
	@Step("Then the final note will be equals to {0}")
	public void finalNoteChanged(double final_note) {
		log.info("Validating if final note is equal to " + final_note);
		then().body("final_note", equalTo(final_note));
	}

	/**
	 * Verify expected approved status.
	 * 
	 * @param approved boolean
	 */
	@Step("Then the final status will be equals to {0}")
	public void finalApprovedStatus(boolean status) {
		log.info("Validating if the new status is: " + status);
		then().body("approved", equalTo(status));
	}

	/**
	 * Verify expected status code.
	 * 
	 * @param statusCode int
	 */
	@Step("Then the status code will be {0}")
	public void isStatusCode(int statusCode) {
		log.info("Validating expected status code: " + statusCode);
		then().statusCode(statusCode);
	}

	/**
	 * Print list of students.
	 */
	@Step("Then I can see the students list")
	public void showActualStudentsList() {
		List<Student> students = then().contentType(ContentType.JSON).extract().response().jsonPath().getList("$",
				Student.class);
		log.info("GET Students endpoint Response");
		log.info(students);
	}

	/**
	 * Find first user with the name.
	 * 
	 * @param firstName String
	 * @return String with the id
	 */
	@Step("Then I can see the list of students")
	public String getStudentID(String name) {
		log.info("Getting the id that matches with the student first_name, last_name: " + name);
		List<Student> students = then().contentType(ContentType.JSON).extract().response().jsonPath().getList("$",
				Student.class);

		Optional<Student> id = students.stream()
				.filter(student -> name.equals(student.getFirst_name() + " " + student.getLast_name())).findFirst();
		log.info("id found:" + id.get().getId());
		if (id.isPresent())
			return id.get().getId();

		else
			return "";

	}

	/**
	 * Verify expected email.
	 * 
	 * @param email String
	 */
	@Step("Then there should be a email filed with value {0}")
	public void userEmailShouldBe(String email) {
		log.info("The email should be: " + email);
		response.then().body("email", equalTo(email));
	}
}

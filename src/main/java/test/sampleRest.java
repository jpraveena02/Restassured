package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class sampleRest {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		// login
		Response loginRes = RestAssured.given().headers("Content-Type", "application/json").when()
				.body("{\"username\": \"jpraveenarao@tekarch.com\", \"password\": \"Admin123\"}").post("/login").then()
				.statusCode(201).extract().response();

		loginRes.prettyPrint();
		System.out.println(loginRes.statusCode());
		String token = loginRes.jsonPath().get("[0].token");
		System.out.println(token);
		// addUser
		Response addUserRes = RestAssured.given().headers("Content-Type", "application/json").when().body(
				"{\"accountno\": \"TA-1234567\", \"departmentno\": \"2\", \"salary\": \"30001\", \"pincode\": \"505327\"}")
				.post("/addData");
		addUserRes.prettyPrint();

		// get
		Response getRes = RestAssured.given().header("Content-Type", "application/json").header("token", token)
				.when().get("/getdata");
		getRes.prettyPrint();

	}

}

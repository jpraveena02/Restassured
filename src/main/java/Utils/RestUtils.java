package Utils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import java.io.File;
import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
	
	
	public static Response postReq(HashMap<String, String> headers, Object payload, String path) {
		Response userResponse = RestAssured.given().headers(headers).when().body(payload).post(path).then()
				.statusCode(201).extract().response();
		return userResponse;
	}

	public static Response postReqInvalid(HashMap<String, String> headers, Object payload, String path) {
		Response userResponse = RestAssured.given().headers(headers).when().body(payload).post(path).then()
				.statusCode(401).extract().response();
		return userResponse;
	}

	public static Response postReqLogout(HashMap<String, String> headers, Object payload, String path) {
		Response userResponse = RestAssured.given().headers(headers).when().body(payload).post(path).then()
				.statusCode(200).extract().response();
		return userResponse;
		
	}

	public static Response putReq(HashMap<String, String> headers, String payload, String path) {
		Response putRes = RestAssured.given().headers(headers).when().body(payload).put(path).then().statusCode(200)
				.extract().response();

		return putRes;

	}

	public static Response getReq(HashMap<String, String> headers, String path) {
		Response getRes = RestAssured.given().headers(headers).when().get(path).then().statusCode(200).extract()
				.response();
		return getRes;

	}

	public static Response deleteReq(HashMap<String, String> headers, String payload, String path) {
		Response delRes = RestAssured.given().headers(headers).when().body(payload).delete(path).then().statusCode(200)
				.extract().response();

		return delRes;
	}

	public static void validateSchema(Response response, String filePath) {
		response.then().assertThat().body(matchesJsonSchema(new File(filePath)));
	}

	/*
	 * public static void validateFirstElementOfSchema(Response response, String
	 * filePath) { response.then().assertThat().body("$[0]", matchesJsonSchema(new
	 * File(filePath))); }
	 */

}

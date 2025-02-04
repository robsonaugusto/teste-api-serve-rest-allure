package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthHelper {
    private static String token;

    public static void authenticate() {
        if (token == null) { // Evita múltiplas autenticações desnecessárias
            Map<String, String> login = new HashMap<>();
            login.put("email", "testeteRB@qa.com.br");
            login.put("password", "teste");

            token = given()
                    .contentType(ContentType.JSON)
                    .body(login)
                    .when()
                    .post("/login")
                    .then()
                    .statusCode(200)
                    .extract().path("token");

            RestAssured.requestSpecification = getAuthenticatedRequest();
        }
    }

    public static RequestSpecification getAuthenticatedRequest() {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "JWT " + token);
    }
}

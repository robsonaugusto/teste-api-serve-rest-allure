package core;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import utils.AuthHelper;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://serverest.dev"; // URL base da API
        AuthHelper.authenticate(); // Realiza o login antes dos testes
    }
}
package tests;

import core.BaseTest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import utils.AuthHelper;
import utils.JsonUtils;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Feature("Serverest API")
@TestMethodOrder(OrderAnnotation.class)
public class Usuarios extends BaseTest {

    private static final String JSON_PATH = "src/test/resources/CadastrarUsuario.json";
    private static String userId; // Variável estática para armazenar o ID do usuário

    @Test
    @Order(1) // Garantindo que rode antes de outros testes
    @DisplayName("Endpoints Para Cadastro de Usuario")
    public void testCadastrarUsuario() {
        Response response = createUser();

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.asString());

        assertEquals(201, response.statusCode(), "Erro: Código de status inesperado!");

        // Extrai o ID e salva para uso em outros testes
        userId = response.jsonPath().getString("_id");
        System.out.println("Usuário criado com ID: " + userId);
    }

    private Response createUser() {
        Map<String, Object> userBody = JsonUtils.getJsonBodyWithRandomEmail(JSON_PATH);

        return AuthHelper.getAuthenticatedRequest()
                .contentType("application/json")
                .body(userBody)
                .when()
                .post("/usuarios")
                .then()
                .extract().response();
    }

    @Test
    @Order(3)
    public void testBuscarUsuarioCriado() {

        Response response = AuthHelper.getAuthenticatedRequest()
                .when()
                .get("/usuarios/" + userId) // Utilizando o ID salvo
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.asString());

        assertEquals(200, response.statusCode(), "Erro: Não foi possível buscar o usuário criado!");
    }


    @Test
    @Order(4)
    public void testExcluirUsuarioCriado() {

        Response response = AuthHelper.getAuthenticatedRequest()
                .when()
                .delete("/usuarios/" + userId) // Utilizando o ID salvo
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.asString());

        assertEquals(200, response.statusCode(), "Erro: Não foi possível Excluir o usuário!");
    }


}
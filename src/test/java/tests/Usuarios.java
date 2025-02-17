package tests;

import core.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.AuthHelper;
import utils.JsonUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Serverest API")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Usuarios extends BaseTest {

    private static final String JSON_PATH = "src/test/resources/CadastrarUsuario.json";
    private static String userId; // Variável estática para armazenar o ID do usuário

    @Test
    @Order(1)
    @Story("Cadastro de Usuário")
    @DisplayName("Cadastrar um novo usuário")
    @Description("Este teste cadastra um novo usuário na API Serverest e valida a resposta.")
    public void testCadastrarUsuario() {
        Response response = createUser();
        logResponse(response, "Cadastro de usuário");

        assertEquals(201, response.statusCode(), "Erro: Código de status inesperado!");

        // Extrai o ID e salva para uso em outros testes
        userId = response.jsonPath().getString("_id");
        System.out.println("Usuário criado com ID: " + userId);
    }

    @Step("Criando um novo usuário")
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
    @Story("Consulta de Usuário")
    @DisplayName("Buscar usuário cadastrado")
    @Description("Este teste busca um usuário previamente cadastrado usando o ID gerado no teste de criação.")
    public void testBuscarUsuarioCriado() {
        Response response = AuthHelper.getAuthenticatedRequest()
                .when()
                .get("/usuarios/" + userId)
                .then()
                .extract().response();

        logResponse(response, "Buscar usuário criado");

        assertEquals(200, response.statusCode(), "Erro: Não foi possível buscar o usuário criado!");
    }

    @Test
    @Order(4)
    @Story("Exclusão de Usuário")
    @DisplayName("Excluir usuário cadastrado")
    @Description("Este teste exclui um usuário previamente cadastrado usando o ID gerado no teste de criação.")
    public void testExcluirUsuarioCriado() {
        Response response = AuthHelper.getAuthenticatedRequest()
                .when()
                .delete("/usuarios/" + userId)
                .then()
                .extract().response();

        logResponse(response, "Excluir usuário criado");

        assertEquals(200, response.statusCode(), "Erro: Não foi possível excluir o usuário!");
    }

    @Step("Log da resposta da API para {operation}")
    private void logResponse(Response response, String operation) {
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.asString());
        Allure.addAttachment(operation, response.asString());
    }
}

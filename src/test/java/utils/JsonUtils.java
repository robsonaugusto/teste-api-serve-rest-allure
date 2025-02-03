package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    public static Map<String, Object> getJsonBodyWithRandomEmail(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Lê o JSON como um Map
            Map<String, Object> jsonData = objectMapper.readValue(new File(filePath), Map.class);

            // Define um email aleatório
            jsonData.put("email", EmailUtils.generateRandomEmail());

            return jsonData;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler JSON: " + e.getMessage());
        }
    }
}
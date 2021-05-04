package petshop;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class DELETE404 {

    private static final int ID = 4815167;
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void delete404() {
        RestAssured.when()
                .delete(URL + ID);

        RestAssured.when()
                .delete(URL + ID)
                .then()
                .statusCode(404)
                .assertThat();
    }
}

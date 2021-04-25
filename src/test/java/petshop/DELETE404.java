package petshop;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

public class DELETE404 {

    private static final int ID = 4815167;

    @Test
    @Order(1)
    public void delete1() {
        RestAssured.when()
                .delete("https://petstore.swagger.io/v2/pet/" + ID);
    }

    @Test
    @Order(2)
    public void delete2() {
        RestAssured.when()
                .delete("https://petstore.swagger.io/v2/pet/" + ID)
                .then()
                .statusCode(404)
                .assertThat();
    }
}

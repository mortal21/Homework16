package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import pets.newPet.Pet;

public class PUT404 {

    private static final int ID = 48151;

    @Test
    @Order(1)
    public void delete() {
        RestAssured.when()
                .delete("https://petstore.swagger.io/v2/pet/" + ID);
    }

    @Test
    @Order(2)
    public void put() {
        final String changedName = "Changed Doge 48151";
        final String status = "Active";
        Pet myChangedPet = new Pet();
        myChangedPet.setId(ID);
        myChangedPet.setName(changedName);
        myChangedPet.setStatus(status);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myChangedPet)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(404)                                        // Тест упадёт, так как будет 200
                .assertThat();
    }
}

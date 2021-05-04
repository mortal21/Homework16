package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import pets.newPet.Pet;

public class PUT404 {

    private static final int ID = 48151;
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void put404() {
        RestAssured.when()
                .delete(URL + ID);

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
                .put(URL)
                .then()
                .statusCode(404)                                        // Тест упадёт, так как будет 200
                .assertThat();
    }
}

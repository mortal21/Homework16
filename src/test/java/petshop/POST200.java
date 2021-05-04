package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import pets.newPet.Pet;

import static org.hamcrest.CoreMatchers.equalTo;

public class POST200 {

    private static final int ID = 4;
    private static final String NAME = "Doge 4";
    private static final String STATUS = "Active";
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void post200() {
        Pet myPet1 = new Pet();
        myPet1.setId(ID);
        myPet1.setName(NAME);
        myPet1.setStatus(STATUS);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet1)
                .post(URL)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(NAME))
                .body("status", equalTo(STATUS));

        RestAssured.when()
                .get(URL + ID)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(NAME))
                .body("status", equalTo(STATUS));
    }
}

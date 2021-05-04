package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import pets.newPet.Pet;

import static org.hamcrest.CoreMatchers.equalTo;

public class GET200 {

    private static final int ID = 48;
    private static final String NAME = "Doge 48";
    private static final String STATUS = "Active";
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void get200() {
        Pet myPet2 = new Pet();
        myPet2.setId(ID);
        myPet2.setName(NAME);
        myPet2.setStatus(STATUS);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet2)
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

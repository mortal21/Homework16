package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import pets.newPet.Pet;

import static org.hamcrest.CoreMatchers.equalTo;

public class DELETE200 {

    private static final int ID = 481516;
    private static final String NAME = "Doge 481516";
    private static final String STATUS = "Active";
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void delete200() {
        Pet myPet5 = new Pet();
        myPet5.setId(ID);
        myPet5.setName(NAME);
        myPet5.setStatus(STATUS);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet5)
                .post(URL)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(NAME))
                .body("status", equalTo(STATUS));

        RestAssured.when()
                .delete(URL + ID)
                .then()
                .statusCode(200)
                .assertThat();

        RestAssured.when()
                .get(URL + ID)
                .then()
                .statusCode(404)
                .assertThat()
                .body("name", equalTo(null))
                .body("status", equalTo(null));
    }
}

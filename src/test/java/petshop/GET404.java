package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import pets.newPet.Pet;

import static org.hamcrest.CoreMatchers.equalTo;

public class GET404 {

    private static final int ID = 481;
    private static final String NAME = "Doge 481";
    private static final String STATUS = "Active";
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void get404() {
        Pet myPet3 = new Pet();
        myPet3.setId(ID);
        myPet3.setName(NAME);
        myPet3.setStatus(STATUS);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet3)
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

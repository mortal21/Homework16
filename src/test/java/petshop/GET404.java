package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import pets.newPet.Pet;

import static org.hamcrest.CoreMatchers.equalTo;

public class GET404 {

    private static final int ID = 481;
    private static final String NAME = "Doge 481";
    private static final String STATUS = "Active";

    @Test
    @Order(1)
    public void post() {
        Pet myPet3 = new Pet();
        myPet3.setId(ID);
        myPet3.setName(NAME);
        myPet3.setStatus(STATUS);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet3)
                .post("https://petstore.swagger.io/v2/pet/")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(NAME))
                .body("status", equalTo(STATUS));
    }

    @Test
    @Order(2)
    public void delete() {
        RestAssured.when()
                .delete("https://petstore.swagger.io/v2/pet/" + ID)
                .then()
                .statusCode(200)
                .assertThat();
    }

    @Test
    @Order(3)
    public void get() {
        RestAssured.when()
                .get("https://petstore.swagger.io/v2/pet/" + ID)
                .then()
                .statusCode(404)
                .assertThat()
                .body("name", equalTo(null))
                .body("status", equalTo(null));
    }
}

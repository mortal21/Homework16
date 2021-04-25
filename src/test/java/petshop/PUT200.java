package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import pets.newPet.Pet;

import static org.hamcrest.CoreMatchers.equalTo;

public class PUT200 {

    private static final int ID = 4815;
    private static final String NAME = "Doge 4815";
    private static final String CHANGEDNAME = "Changed Doge 4815";
    private static final String STATUS = "Active";

    @Test
    @Order(1)
    public void post() {
        Pet myPet4 = new Pet();
        myPet4.setId(ID);
        myPet4.setName(NAME);
        myPet4.setStatus(STATUS);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet4)
                .post("https://petstore.swagger.io/v2/pet/")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(NAME))
                .body("status", equalTo(STATUS));
    }

    @Test
    @Order(2)
    public void get() {
        RestAssured.when()
                .get("https://petstore.swagger.io/v2/pet/" + ID)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(NAME))
                .body("status", equalTo(STATUS));
    }

    @Test
    @Order(3)
    public void put() {
        Pet myChangedPet = new Pet();
        myChangedPet.setId(ID);
        myChangedPet.setName(CHANGEDNAME);
        myChangedPet.setStatus(STATUS);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myChangedPet)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(CHANGEDNAME))
                .body("status", equalTo(STATUS));
    }

    @Test
    @Order(4)
    public void get2() {
        RestAssured.when()
                .get("https://petstore.swagger.io/v2/pet/" + ID)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(CHANGEDNAME))
                .body("status", equalTo(STATUS));
    }
}

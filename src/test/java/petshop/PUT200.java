package petshop;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Test;
import pets.newPet.Pet;

import static org.hamcrest.CoreMatchers.equalTo;

public class PUT200 {

    private static final int ID = 4815;
    private static final String NAME = "Doge 4815";
    private static final String CHANGEDNAME = "Changed Doge 4815";
    private static final String STATUS = "Active";
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    @Test
    public void put200() {
        Pet myPet4 = new Pet();
        myPet4.setId(ID);
        myPet4.setName(NAME);
        myPet4.setStatus(STATUS);

        Pet myChangedPet = new Pet();
        myChangedPet.setId(ID);
        myChangedPet.setName(CHANGEDNAME);
        myChangedPet.setStatus(STATUS);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet4)
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

        RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myChangedPet)
                .when()
                .put(URL)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(CHANGEDNAME))
                .body("status", equalTo(STATUS));

        RestAssured.when()
                .get(URL + ID)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(CHANGEDNAME))
                .body("status", equalTo(STATUS));
    }
}

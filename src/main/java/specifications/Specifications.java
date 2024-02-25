package specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.log4j.Log4j;

@Log4j
public class Specifications {

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecification200() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecification201() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(201)
                .build();
    }

    public static ResponseSpecification responseSpecification204() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(204)
                .build();
    }

    public static ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpecification400() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpecification403() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(403)
                .build();
    }


    public static ResponseSpecification responseSpecification406() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(406)
                .build();
    }



    public static void installSpecifications(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}

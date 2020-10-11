package apiEngine;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

public class EndPoint {

    private static final String DATA = "/data";
    private static final String VERSION = "/2.5";
    private static final String APP_ID = "7fe67bf08c80ded756e598d6f8fedaea";
    private final RequestSpecBuilder requestBuilder;
    private final ResponseSpecBuilder responseSpecBuilder;
    private ResponseSpecification responseSpec;


    public EndPoint(String baseUrl) {
        requestBuilder = new RequestSpecBuilder();
        responseSpecBuilder = new ResponseSpecBuilder();
        buildRequestSpec(baseUrl);
    }

    public void buildRequestSpec(String baseUrl) {
        requestBuilder.setBaseUri(baseUrl);
        requestBuilder.setBasePath(DATA + VERSION);
        requestBuilder.addHeader("Content-Type", "application/json");
        requestBuilder.addQueryParam("appid", APP_ID);
    }

    public ResponseSpecification buildResponseSpec(int expected_status_code, ContentType type){
        return responseSpecBuilder.
                expectStatusCode(expected_status_code).
                expectContentType(type).
                build();
    }

   public void addQueryParams(Map<String, String> params) {
        params.forEach(requestBuilder::addQueryParam);
    }

    public Response getWeatherFromAPIWithParams(Map<String, String> params) {
        addQueryParams(params);
        responseSpec = buildResponseSpec(200, ContentType.JSON);
        return APIHandler.get("/weather", requestBuilder, responseSpec);
    }

}

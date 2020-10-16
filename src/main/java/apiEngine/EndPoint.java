package apiEngine;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class EndPoint {

    private static final String DATA = "/data";
    private static final String VERSION = "/2.5";
    private static final String APP_ID = "7fe67bf08c80ded756e598d6f8fedaea";
    private final RequestSpecBuilder requestBuilder;
    private final ResponseSpecBuilder responseSpecBuilder;
    private ResponseSpecification responseSpec;
    private static final Logger LOG = LoggerFactory.getLogger(EndPoint.class);


    public EndPoint(String baseUrl) {
        LOG.info("Setting up endpoint {} for executing request", baseUrl);
        requestBuilder = new RequestSpecBuilder();
        responseSpecBuilder = new ResponseSpecBuilder();
        requestBuilder.setBaseUri(baseUrl);
        buildRequestSpec();
    }

    public void buildRequestSpec() {
        LOG.info("Building request specification");
        requestBuilder.setBasePath(DATA + VERSION);
        requestBuilder.addHeader("Content-Type", "application/json");
        requestBuilder.addQueryParam("appid", APP_ID);
    }

    public ResponseSpecification buildResponseSpec(int expected_status_code, ContentType type) {
        LOG.info("Building response specification");
        return responseSpecBuilder.
                expectStatusCode(expected_status_code).
                expectContentType(type).
                build();
    }

   public void addQueryParams(Map<String, String> params) {
        LOG.info("Adding query parameters");
       for (Map.Entry<String, String> entry : params.entrySet()) {
           String key = entry.getKey();
           String value = entry.getValue();
           requestBuilder.addQueryParam(key, value);
       }
   }

    public Response getWeatherFromAPIWithParams(Map<String, String> params) {
        addQueryParams(params);
        responseSpec = buildResponseSpec(200, ContentType.JSON);
        return APIHandler.get("/weather", requestBuilder, responseSpec);
    }

}

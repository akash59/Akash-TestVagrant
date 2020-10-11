package apiEngine;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class APIHandler {

    /**
     * Static method for making Rest-Assured GET requests.
     *
     * @param restAPI The specific API to test ex: "/myApi"
     * @param reqBuilder Rest-Assured RequestSpecBuilder for constructing RA request
     * @param expectedResponse Rest-Assured ResponseSpecification for validating the response
     * @return Response
     */
    public static Response get(String restAPI, RequestSpecBuilder reqBuilder, ResponseSpecification expectedResponse) {
        System.out.println(restAPI);
        return fireRestCall(restAPI, reqBuilder, expectedResponse, Method.GET);
    }

    /**
     * Static method for making Rest-Assured POST requests.
     *
     * @param restAPI The specific API to test ex: "/myApi"
     * @param reqBuilder Rest-Assured RequestSpecBuilder for constructing RA request
     * @param expectedResponse Rest-Assured ResponseSpecification for validating the response
     * @return Response
     */
    public static Response post(String restAPI, RequestSpecBuilder reqBuilder,
                                ResponseSpecification expectedResponse) {
        return fireRestCall(restAPI, reqBuilder, expectedResponse, Method.POST);
    }

    /**
     * Static method for making Rest-Assured REST requests.
     *
     * @param restAPI The specific API to test ex: "/myApi"
     * @param reqBuilder Rest-Assured RequestSpecBuilder for constructing RA request
     * @param expectedResponse Rest-Assured ResponseSpecification for validating the response
     * @param method http action to be invoked (i.e. POST, GET, etc.)
     * @return Response
     */
    public static Response fireRestCall(String restAPI,
                                        RequestSpecBuilder reqBuilder, ResponseSpecification expectedResponse, Method method) {

        RequestSpecification req = prepRequest(reqBuilder);

        switch (method) {
            case DELETE:
                return given(req).expect().spec(expectedResponse).log().all().when().delete(restAPI);
            case GET:
                return given(req).expect().spec(expectedResponse).log().all().when().get(restAPI);
            case HEAD:
                return given(req).expect().spec(expectedResponse).log().all().when().head(restAPI);
            case OPTIONS:
                return given(req).expect().spec(expectedResponse).log().all().when().options(restAPI);
            case PATCH:
                return given(req).expect().spec(expectedResponse).log().all().when().patch(restAPI);
            case POST:
                return given(req).expect().spec(expectedResponse).log().all().when().post(restAPI);
            case PUT:
                return given(req).expect().spec(expectedResponse).log().all().when().put(restAPI);
            case TRACE:
                return given(req).expect().spec(expectedResponse).log().all().when().request(method,
                        restAPI);
            default:
                return null;
        }
    }

    private static RequestSpecification prepRequest(RequestSpecBuilder reqBuilder) {
        RequestSpecification req;
        //TestProperties tp = TestProperties.getInstance();
        //TargetServiceConfiguration targetService = tp.getTargetServiceConfig(serviceName);
        //reqBuilder.setBaseUri(targetService.getURL());
        req = reqBuilder.build();
        return req;
    }
}

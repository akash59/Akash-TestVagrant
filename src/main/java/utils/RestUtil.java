package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestUtil {

    //Global Setup Variables
    public static String path; //Rest request path

    /**
     ***Sets Base URI***
     Before starting the test, we should set the RestAssured.baseURI
     */
    public static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }

    /**
     ***Sets base path***
     Before starting the test, we should set the RestAssured.basePath
     */
    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }

    /**
     ***Reset Base URI (after test)***
     After the test, we should reset the RestAssured.baseURI
     */
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    /**
     ***Reset base path (after test)***
     After the test, we should reset the RestAssured.basePath
     */
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    /**
     ***Sets ContentType***
     We should set content type as JSON or XML before starting the test
     */
    public static void setContentType (ContentType Type){
        given().contentType(Type);
    }

    /**
     ***search query path of first example***
     It is  equal to "barack obama/videos.json?num_of_videos=4"
     */
    public static void  createSearchQueryPath(String searchTerm, String jsonPathTerm, String param, String paramValue) {
        path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
    }

}

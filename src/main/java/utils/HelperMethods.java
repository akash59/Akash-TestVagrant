package utils;

import io.restassured.response.Response;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class HelperMethods {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void setAnsiGreen(String msg){
        System.out.println(ANSI_GREEN +  msg  + ANSI_RESET) ;
    }

    public static void setAnsiRed(String msg){
        System.out.println(ANSI_RED +  msg  + ANSI_RESET) ;
    }


    /**
     Verify the http response status returned. Check Status Code is 200?
     We can use Rest Assured library's response's getStatusCode method
     */
    public static void checkStatusIs200 (Response res) {
        Assert.assertEquals(res.getStatusCode(),  200, "Status Check Failed!");
    }

    public static void checkStatusIs201 (Response res) {
        Assert.assertEquals(res.getStatusCode(),  201, "Status Check Failed!");
    }

    public static void checkResponseTime (Response resp, String msg ) {
        Long time = resp.then().extract().time();
        System.out.println("SmartResponse Time of  "+ msg + time + " ms");
    }

    public static void printResponse( Response resp){
        System.out.println(resp.asString());
    }

    public static void assertCode(String functionalityName, int responseCode, int expectedCode, String reponseJson){
        if ( responseCode == expectedCode){
            HelperMethods.setAnsiGreen(functionalityName +" Functionality Completed Successfully");
        }
        else {
            HelperMethods.setAnsiRed(functionalityName +" Functionality fails with Response Code = " +  responseCode);
            HelperMethods.setAnsiRed(reponseJson);
            assertEquals(responseCode, expectedCode);
        }
    }
}

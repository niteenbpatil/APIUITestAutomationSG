package ApiUtils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.User;
import utils.ReadConfigFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestAssuredExtension {

    RequestSpecBuilder builder = new RequestSpecBuilder();
    public static RequestSpecification request;

    static String baseURI = ReadConfigFile.readConfig("baseURI");
    static String basePath = ReadConfigFile.readConfig("basePath");

    public RestAssuredExtension() {
        builder.setBaseUri(baseURI);
        builder.setBasePath(basePath);
        builder.setContentType(ContentType.JSON);

        RequestSpecification requestSpec = builder.build();
        request = RestAssured.given().spec(requestSpec);
    }

    public static ResponseOptions<Response> POSTOpsWithBody(String url, Map<String, String> body) throws URISyntaxException {
        request.body(body);
        System.out.println("URI formed=> " + baseURI + basePath + url);
        return request.post(new URI(baseURI + basePath + url));
    }

    public static ResponseOptions<Response> GetOps(String URL) throws URISyntaxException {
        return request.get(new URI(baseURI + basePath + URL));

    }

    /*public static User createUser(String username, String jobTitle, String url) throws URISyntaxException {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", username);
        requestBody.put("job", jobTitle);
        return request.post(new URI(baseURI + basePath + url)).as(User.class);

    }*/
    public static ResponseOptions<Response> DeleteOps(String pathParams) throws URISyntaxException {
        return request.delete(pathParams);
    }
}

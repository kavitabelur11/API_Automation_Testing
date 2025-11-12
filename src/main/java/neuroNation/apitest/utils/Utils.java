package neuroNation.apitest.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {

	public static RequestSpecification req;

    public RequestSpecification getRequestSpecification() throws IOException
    {
        req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                .setContentType(ContentType.JSON).build();
        System.out.println(req);
        return req;
    }

    public RequestSpecification postRequestSpecification() throws IOException
    {
            req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addPathParam("userRegister","register")
                    .setContentType(ContentType.JSON).build();
            System.out.println(req);
            return req;
    }


	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("/Users/supreethbelur/IdeaProjects/API_Automation_Testing/src/main/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

}

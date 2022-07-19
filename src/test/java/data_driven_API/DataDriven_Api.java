package data_driven_API;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dataHandler.DataHandler;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
//import org.junit.jupiter.api.BeforeAll;

public class DataDriven_Api {
	
	DataHandler d = new DataHandler();
	String productId="";
	
    @BeforeTest 
    public void setup() {
		RestAssured.baseURI ="https://reqres.in/";
	}
    
	@Test(enabled = true, priority = 1)
	public void postUser () throws Exception {
		String datapath = System.getProperty("user.dir");
		datapath = datapath + "/src/main/resources/TestData.xlsx";
		System.out.println("Excel path is : "+datapath);
		
		//for reading the data from excel file...
		
		Object nam = d.Read_Excel(datapath, 1, 1);
		String name = nam.toString();
		
		Object j = d.Read_Excel(datapath, 1, 2);
		String job = j.toString();
		
		//RestAssured.baseURI ="";
		JSONObject bodyparam = new JSONObject();  
		bodyparam.put("name", name);
		bodyparam.put("job", job);
		
		
		RequestSpecification req = RestAssured.given().log().all();
		req.body(bodyparam.toString());
		//given().body(bodyparam.toString()).when().post("api/users")
		
		Response resp = req.post("api/users");
		String headers = resp.getHeaders().toString();
		System.out.println(headers);
		productId=req.post("api/users")
				   .then()
				   .extract().path("id");
		
		
		
		
		
		ResponseBody body = resp.getBody();
		String ok1 = body.asPrettyString();
		
		System.out.println(ok1);
		
		
		System.out.println("User reponse is : "+resp.toString());
		System.out.println("Body is : "+ok1);
		System.out.println("Status code : "+resp.statusCode());
		resp.then().assertThat().statusCode(201);
		
		
		
		if (resp.statusCode()==201)
		{ 
			//If Test case passed writing below data...
			d.Write_Data(datapath, "Passed", 1, 3, CellType.STRING);
			d.Write_Data(datapath, resp.statusCode(), 1, 4, CellType.NUMERIC);
			d.Write_Data(datapath, resp.toString(), 1, 5, CellType.STRING);
			d.Write_Data(datapath, ok1, 1, 6, CellType.STRING);
			d.Write_Data(datapath, headers , 1, 7, CellType.STRING);
		}
		else
		{ 
			// if test case failed write below data...
		d.Write_Data(datapath, "Failed", 1, 3, CellType.STRING);
		d.Write_Data(datapath, resp.statusCode(), 1, 4, CellType.NUMERIC);
		d.Write_Data(datapath, resp.toString(), 1, 5, CellType.STRING);
		d.Write_Data(datapath, ok1, 1, 6, CellType.STRING);
		d.Write_Data(datapath, headers , 1, 7, CellType.STRING);
		}
			
			
			
		
	}
	@Test(enabled = true, priority = 2)
	public void GetUsers() throws IOException
	{
		
		String datapath = System.getProperty("user.dir");
		datapath = datapath + "/src/main/resources/TestData.xlsx";
		System.out.println("Excel path is : "+datapath);
		
		RequestSpecification req = RestAssured.given().log().all();
		
		Response resp = req.get("api/users");
		String ok = resp.getBody().asPrettyString();
		System.out.println(ok);
		
		
		
		if (resp.statusCode()==200)
		{ 
			//If Test case passed writing below data...
			d.Write_Data(datapath, "Passed", 2, 3, CellType.STRING);
			d.Write_Data(datapath, resp.statusCode(), 2, 4, CellType.NUMERIC);
			d.Write_Data(datapath, resp, 2, 5, CellType.STRING);
			d.Write_Data(datapath, ok, 2, 5, CellType.STRING);
		}
		else
		{ 
			// if test case failed write below data...
		d.Write_Data(datapath, "Failed", 2, 3, CellType.STRING);
		d.Write_Data(datapath, resp.statusCode(), 2, 4, CellType.NUMERIC);
		d.Write_Data(datapath, resp.getBody().asString(), 2, 5, CellType.STRING);
		}
		
		
		
		
		
	}
	@Test (enabled = true, priority = 3)
	public void UpdateUser() throws Exception {
		
		String datapath = System.getProperty("user.dir");
		datapath = datapath + "/src/main/resources/TestData.xlsx";
		System.out.println("Excel path is : "+datapath);
		
		
		
		Object j = d.Read_Excel(datapath, 3, 2);
		String job = j.toString();
		
		JSONObject bodyparam = new JSONObject();  
		bodyparam.put("job", job);
		
		RequestSpecification req = RestAssured.given().log().all();
		req.body(bodyparam.toString());
		
		Response resp = req.put("api/users/"+productId);
		String headers = resp.getHeaders().toString();
		System.out.println(headers);
		
		ResponseBody body = resp.getBody();
		String ok1 = body.asPrettyString();
		
		
		System.out.println(ok1);
		
		
		System.out.println("User reponse is : "+resp.toString());
		System.out.println("Body is : "+ok1);
		System.out.println("Status code : "+resp.statusCode());
		resp.then().assertThat().statusCode(200);
		
		
		
		if (resp.statusCode()==200)
		{ 
			//If Test case passed writing below data...
			d.Write_Data(datapath, "Passed", 3, 3, CellType.STRING);
			d.Write_Data(datapath, resp.statusCode(), 3, 4, CellType.NUMERIC);
			d.Write_Data(datapath, resp.toString(), 3, 5, CellType.STRING);
			d.Write_Data(datapath, ok1, 3, 6, CellType.STRING);
			d.Write_Data(datapath, headers , 3, 7, CellType.STRING);
		}
		else
		{ 
			// if test case failed write below data...
		d.Write_Data(datapath, "Failed", 3, 3, CellType.STRING);
		d.Write_Data(datapath, resp.statusCode(), 3, 4, CellType.NUMERIC);
		d.Write_Data(datapath, resp.toString(), 3, 5, CellType.STRING);
		d.Write_Data(datapath, ok1, 3, 6, CellType.STRING);
		d.Write_Data(datapath, headers , 3, 7, CellType.STRING);
		}
		
		
		
		
		
		
	}
	@Test(enabled = true, priority = 4)
	public void DeleteUsers() throws IOException
	{
		String datapath = System.getProperty("user.dir");
		datapath = datapath + "/src/main/resources/TestData.xlsx";
		System.out.println("Excel path is : "+datapath);
		
		RequestSpecification req = RestAssured.given().log().all();
		
		Response resp = req.delete("api/users/"+productId);
		String ok = resp.getBody().asPrettyString();
		System.out.println(ok);
		
		if (resp.statusCode()==202 || resp.statusCode()==204)
		{ 
			//If Test case passed writing below data...
			d.Write_Data(datapath, "Passed", 4, 3, CellType.STRING);
			d.Write_Data(datapath, resp.statusCode(), 4, 4, CellType.NUMERIC);
			d.Write_Data(datapath, resp.toString(), 4, 5, CellType.STRING);
			d.Write_Data(datapath, ok, 4, 6, CellType.STRING);
			
		}
		else
		{ 
			// if test case failed write below data...
		d.Write_Data(datapath, "Failed", 4, 3, CellType.STRING);
		d.Write_Data(datapath, resp.statusCode(), 4, 4, CellType.NUMERIC);
		d.Write_Data(datapath, resp.toString(), 4, 5, CellType.STRING);
		d.Write_Data(datapath, ok, 4, 6, CellType.STRING);
		
		}
		
	}

}

package com.gaboverriding.rest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class InvocaRest {


	    public static void main(String args[]) {
	    	
	    	RestTemplate restTemplate = new RestTemplate();
	    	String urlString = "http://150.250.250.119:7275/TechArchitecturemx/mx/grantingTicket/V01";
	    	
	    	// create request body
	    	JSONObject json_request = new JSONObject(); // alfinal deja las {}	    	
	    	JSONObject json_authentication = new JSONObject(); // alfinal deja las {}
	    	JSONObject json_userPreferences = new JSONObject(); // alfinal deja las {}	    	
	    	json_authentication.put("consumerId", "00000000");
	    	json_authentication.put("authenticationType", "0");
	    	json_authentication.put("userId", "ADMINF");
	    	json_authentication.put("accessCode", "4152310000799792");
	    	json_userPreferences.put("language", "es");
	    	
	    	JSONObject jsonObject_authenticationData = new JSONObject();
	    	JSONArray listJSONArray = new JSONArray(); //al final deja los []
	    	JSONArray list1 = new JSONArray();
	    	list1.put("tf4rf3IMQ3naa9pipZl7SvAMEygJK8EGgc1OdfGak0AqtvoD1je8VuxvmTYcarHC");
	    	//list1.put("2");
	    	//list1.put("3");
	    	//list1.put("authenticationData", "tf4rf3IMQ3naa9pipZl7SvAMEygJK8EGgc1OdfGak0AqtvoD1je8VuxvmTYcarHC");
	    	

	    	jsonObject_authenticationData.put("authenticationData", list1);	    	
	    	jsonObject_authenticationData.put("idAuthenticationData", "iv_ticketService");
	    	
	    	listJSONArray.put(jsonObject_authenticationData);
	    	
	    	System.out.println(jsonObject_authenticationData);
	    	System.out.println(listJSONArray);
	    	json_authentication.put("authenticationData", listJSONArray);
	    	System.out.println(json_authentication);
	    	json_request.put("authentication", json_authentication);
	    	json_request.put("userPreferences", json_userPreferences);	    	
	    	System.out.println(json_request);
	    	
	    	// set headers
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	    	HttpEntity<String> entity = new HttpEntity<String>(json_request.toString(), headers);

	    	// send request and parse result	    	
	    	ResponseEntity<String> loginResponse = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
	    	if (loginResponse.getStatusCode() == HttpStatus.OK) {
	    	  JSONObject userJson = new JSONObject(loginResponse.getBody());
	    	} else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	    	  // nono... bad credentials
	    		System.out.println();
	    	} else {
	    		System.out.println("respuesta:"+loginResponse.getStatusCode());
	    	}
	    	/*
	    	String jsonText = "{\"first\": 123, \"second\": [4, 5, 6], \"third\": 789}";
	    	  JSONParser parser = new JSONParser();
	    	  ContainerFactory containerFactory = new ContainerFactory(){
	    	    public List creatArrayContainer() {
	    	      return new LinkedList();
	    	    }

	    	    public Map createObjectContainer() {
	    	      return new LinkedHashMap();
	    	    }
	    	                        
	    	  };
	    	                
	    	  try{
	    	    Map json = (Map)parser.parse(jsonText, containerFactory);
	    	    Iterator iter = json.entrySet().iterator();
	    	    System.out.println("==iterate result==");
	    	    while(iter.hasNext()){
	    	      Map.Entry entry = (Map.Entry)iter.next();
	    	      System.out.println(entry.getKey() + "=>" + entry.getValue());
	    	    }
	    	                        
	    	    System.out.println("==toJSONString()==");
	    	    System.out.println(JSONValue.toJSONString(json));
	    	  }
	    	  catch(ParseException pe){
	    	    System.out.println(pe);
	    	  }	    	
	        */
	        /*Prueba page = restTemplate.postForObject("http://150.250.250.119:7275/TechArchitecturemx/mx/grantingTicket/V01", Prueba.class);
	        System.out.println("Name:    " + page.getName());
	        System.out.println("About:   " + page.getAbout());
	        System.out.println("Phone:   " + page.getPhone());
	        System.out.println("Website: " + page.getWebsite());*/
	    }	
	
}

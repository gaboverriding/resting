package com.gaboverriding.rest;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;





import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;





import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class PruebaGJSON {
	
	// My stored keys and values from the json object
	HashMap<String,String> myKeyValues = new HashMap<String,String>();

	// Used for constructing the path to the key in the json object
	Stack<String> key_path = new Stack<String>();
	
	
	public static void main(String[] arggs) {
		
    	String urlString = "http://150.250.250.119:7275/TechArchitecturemx/mx/grantingTicket/V01";		
    	RestTemplate restTemplate = new RestTemplate();    	
		
		List<AuthenticationData> listaAuthenticationData = new ArrayList<AuthenticationData>();
		AuthenticationData authenticationData = new AuthenticationData();
		authenticationData.setIdAuthenticationData("iv_ticketService");
		List<String> lista = new ArrayList<String>();
		lista.add("tf4rf3IMQ3naa9pipZl7SvAMEygJK8EGgc1OdfGak0AqtvoD1je8VuxvmTYcarHC");
		authenticationData.setAuthenticationData(lista);
		listaAuthenticationData.add(authenticationData);
		
		
		Authentication authentication = new Authentication();
		authentication.setAccessCode("4152310000799792");
		authentication.setAuthenticationData(listaAuthenticationData);
		authentication.setAuthenticationType("0");
		authentication.setConsumerId("00000000");
		authentication.setUserId("ADMINF");
		
		UserPreferences userPreferences = new UserPreferences();
		userPreferences.setLanguage("es");
		
		PayLoad payLoad = new PayLoad();
		payLoad.setAuthentication(authentication);
		payLoad.setUserPreferences(userPreferences);
		
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        System.out.println(gson.toJson(payLoad));
        
    	// set headers
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(payLoad), headers);

    	// send request and parse result	    	
    	ResponseEntity<String> loginResponse = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
    	if (loginResponse.getStatusCode() == HttpStatus.OK) {
    	  JSONObject respuesta = new JSONObject(loginResponse.getBody());
    	  HttpHeaders headersResponse = loginResponse.getHeaders();
    	  
    	  StringBuilder sb = new StringBuilder();
    	  Iterator<Entry<String, List<String>>> iter = headersResponse.entrySet().iterator();
    	  while (iter.hasNext()) {
    	      Entry<String, List<String>> entry = iter.next();
    	      sb.append(entry.getKey());
    	      sb.append('=').append('"');
    	      sb.append(entry.getValue());
    	      sb.append('"');
    	      if (iter.hasNext()) {
    	          sb.append(',').append(' ');
    	      }
    	  }
    	  System.out.println("Headers inicio");
    	  System.out.println(sb);
    	  System.out.println("Headers fin");    	  
    	  
    	  //PruebaGJSON prueba = new PruebaGJSON();
    	  //prueba.loadJson(respuesta);
    	  
    	  System.out.println("Payload inicio");
    	  for(int i = 0; i<respuesta.names().length(); i++){
    		    System.out.println("key = " + respuesta.names().getString(i) + "\n---> value = " + respuesta.get(respuesta.names().getString(i)));
    		}    	  
    	  System.out.println("Payload fin");
    	  
    	  
    	} else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
    	  // nono... bad credentials
    		System.out.println();
    	} else {
    		System.out.println("respuesta:"+loginResponse.getStatusCode());
    	}

    	
	}

	// Recursive function that goes through a json object and stores 
	// its key and values in the hashmap 	
	private void loadJson(JSONObject json){
	    Iterator<?> json_keys = json.keys();

	    while( json_keys.hasNext() ){
	        String json_key = (String)json_keys.next();

	        try{
	            key_path.push(json_key);
	            loadJson(json.getJSONObject(json_key));
	       }catch (JSONException e){
	           // Build the path to the key
	           String key = "";
	           for(String sub_key: key_path){
	               key += sub_key+".";
	           }
	           key = key.substring(0,key.length()-1);

	           System.out.println(key+": "+json.getString(json_key));
	           key_path.pop();
	           myKeyValues.put(key, json.getString(json_key));
	        }
	    }
	    if(key_path.size() > 0){
	        key_path.pop();
	    }
	}	
	
}

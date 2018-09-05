package com.gqlauth.testutil;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class AuthRequestFactory {

    private AuthRequestFactory() {
    }

    static HttpEntity<Object> forJson(String json) {
    	return forJson("", json);
    }    
    static HttpEntity<Object> forJson(String jwt, String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(jwt != null && !jwt.isEmpty()) {
        	headers.add("Authorization", "Bearer " + jwt);
        }
        return new HttpEntity<>(json, headers);
    }
}
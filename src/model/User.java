package model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class User {
	public int _id;
	public String name;
	public String username;
	public String password;
	public String email;

	public String toJson() {
		try {
			return JsonConverter.objectMapper.writeValueAsString(this);
		} catch(JsonProcessingException e) {
			return null;
		}
	}

	public static User fromJson(String json) {
		try {
			return JsonConverter.objectMapper.readValue(json, User.class);
		} catch(IOException e) {
			return null;
		}
	}
}

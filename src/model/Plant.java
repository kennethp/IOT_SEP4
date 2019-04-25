package model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class Plant {
	public int _id;
	public String name;
	public int assignedProfile;

	public String toJson() {
		try {
			return JsonConverter.objectMapper.writeValueAsString(this);
		} catch(JsonProcessingException e) {
			return null;
		}
	}

	public static Plant fromJson(String json) {
		try {
			return JsonConverter.objectMapper.readValue(json, Plant.class);
		} catch(IOException e) {
			return null;
		}
	}
}

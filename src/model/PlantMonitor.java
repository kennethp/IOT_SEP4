package model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class PlantMonitor {
	public float humidity;
	public float temperature;
	public float CO2;
	public int wateringPeriod;

	public String toJson() {
		try {
			return JsonConverter.objectMapper.writeValueAsString(this);
		} catch(JsonProcessingException e) {
			return null;
		}
	}

	public static PlantMonitor fromJson(String json) {
		try {
			return JsonConverter.objectMapper.readValue(json, PlantMonitor.class);
		} catch(IOException e) {
			return null;
		}
	}
}

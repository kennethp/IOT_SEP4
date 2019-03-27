package Model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class PlantMonitor {
	float humidity;
	float temperature;
	float CO2;
	int wateringPeriod;

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

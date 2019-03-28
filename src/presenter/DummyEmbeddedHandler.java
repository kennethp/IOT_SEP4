package presenter;

public class DummyEmbeddedHandler implements IEmbeddedHandler {
	@Override
	public void waterPlant(int amount) {

	}

	@Override
	public float getHumidity() {
		return 0;
	}

	@Override
	public float getTemperature() {
		return 0;
	}

	@Override
	public float getCO2() {
		return 0;
	}
}

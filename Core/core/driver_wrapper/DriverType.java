package core.driver_wrapper;

public enum DriverType {
	
	Chrome("Chrome"), Firefox("Firefox"), IE("IE"), Edge("Edge");

	private final String value;

	/**
	 * @author  tuan.le
	 * Instantiates a new driver type.
	 * @param value the value
	 */
	DriverType(String value) {
		this.value = value;
	}

	/**
	 * @author tuan.le
	 * Gets the value.
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}

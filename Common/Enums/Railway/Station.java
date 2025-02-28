package Enums.Railway;

public enum Station {
	SAI_GON("Sài Gòn"), PHAN_THIET("Phan Thiết"), DA_NANG("Đà Nẵng"), NHA_TRANG("Nha Trang"),
	HUE("Huế");
	
	private final String location;
	
	Station(final String location) {
		this.location = location;
	}
	
	public String getStation() {
		return location;
	}
}

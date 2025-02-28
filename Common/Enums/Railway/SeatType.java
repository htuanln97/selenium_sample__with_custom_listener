package Enums.Railway;

public enum SeatType {
	
		HARD_SEAT("Hard seat", "HS"), 
		SOFT_SEAT("Soft_seat", "SS"), 
		SOFT_SEAT_WITH_AIR_CONDITIONER("Soft seat with air conditioner", "SSC"), 
		HARD_BED("Hard bed", "HB"), 
		SOFT_BED("Soft bed", "SB"), 
		SOFT_BED_WITH_AID_CONDITIONER("Soft bed with air conditioner", "SBC");

		private final String seatTypeName;
		private final String seatTypeCode;

		SeatType(final String name, final String code) {
			this.seatTypeName = name;
			this.seatTypeCode = code;
		}

		public String getSeatTypeName() {
			return this.seatTypeName;
		}

		public String getSeatTypeCode() {
			return this.seatTypeCode;
		}


}

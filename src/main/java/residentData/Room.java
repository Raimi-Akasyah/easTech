package residentData;

public class Room {
	private int roomID;
	private String roomType;
    private String roomNumber;
    private String roomBlock;
    private String available;
    
    public Room(int roomID, String roomType, String roomNumber, String roomBlock, String available) {
		super();
		this.roomID = roomID;
		this.roomType = roomType;
		this.roomNumber = roomNumber;
		this.roomBlock = roomBlock;
		this.available = available;
	}
    public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomBlock() {
		return roomBlock;
	}

	public void setRoomBlock(String roomBlock) {
		this.roomBlock = roomBlock;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}


 
}

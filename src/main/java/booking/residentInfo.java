package booking;

import java.util.Date;

public class residentInfo {
    private String customerName;
    private String noIC;
    private String roomType;
    private String roomNumber;
    private String roomBlock;
    private String available;
    private String rentAmount;
    private String rentPaymentStatus;
    private Date rentDue;

    // Constructor and getters/setters
    public residentInfo(String customerName, String noIC, String roomType, String roomNumber,
                        String roomBlock, String available, String rentAmount, 
                        String rentPaymentStatus, Date rentDue) {
        this.customerName = customerName;
        this.noIC = noIC;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.roomBlock = roomBlock;
        this.available = available;
        this.rentAmount = rentAmount;
        this.rentPaymentStatus = rentPaymentStatus;
        this.rentDue = rentDue;
    }

    public String getCustomerName() { return customerName; }
    public String getNoIC() { return noIC; }
    public String getRoomType() { return roomType; }
    public String getRoomNumber() { return roomNumber; }
    public String getRoomBlock() { return roomBlock; }
    public String getAvailable() { return available; }
    public String getRentAmount() { return rentAmount; }
    public String getRentPaymentStatus() { return rentPaymentStatus; }
    public Date getRentDue() { return rentDue; }
}
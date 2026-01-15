package residentData;

public class Customer {
    private int id;
    private String fullName;
    private String noIC;
    private String email;
    private String phoneNum;
    private String dateBirth;
    private String room;
    private String startDate;
    private String endDate;

    // Constructor
    public Customer(int id, String fullName, String noIC, String email, String phoneNum, String dateBirth2, String room, String startDate2, String endDate2) {
        this.id = id;
        this.fullName = fullName;
        this.noIC = noIC;
        this.email = email;
        this.phoneNum = phoneNum;
        this.dateBirth = dateBirth2;
        this.room = room;
        this.startDate = startDate2;
        this.endDate = endDate2;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getNoIC() { return noIC; }
    public void setNoIC(String noIC) { this.noIC = noIC; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getDateBirth() { return dateBirth; }
    public void setDateBirth(String dateBirth) { this.dateBirth = dateBirth; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}

package complaint;

import java.util.Date;

public class complaint {
    private int complaintID;
    private String complaintDescription;  // This will be immediately after complaintID
    private String cmp_priority;
    private String cmp_status;
    private Date cmp_Date;

    public complaint(int complaintID, String complaintDescription, String cmp_priority, String cmp_status, Date cmp_Date) {
        super();
        this.complaintID = complaintID;
        this.complaintDescription = complaintDescription;  // Initialize complaintDescription here
        this.cmp_priority = cmp_priority;
        this.cmp_status = cmp_status;
        this.cmp_Date = cmp_Date;
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public String getCmp_priority() {
        return cmp_priority;
    }

    public void setCmp_priority(String cmp_priority) {
        this.cmp_priority = cmp_priority;
    }

    public String getCmp_status() {
        return cmp_status;
    }

    public void setCmp_status(String cmp_status) {
        this.cmp_status = cmp_status;
    }

    public Date getCmp_Date() {
        return cmp_Date;
    }

    public void setCmp_Date(Date cmp_Date) {
        this.cmp_Date = cmp_Date;
    }
}

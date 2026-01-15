package rent;

import java.util.Date;
public class Rent {
	private int rentID;
	private double rent_amount;
    private String rent_paymentStatus;
    private Date rent_Due;
    
    public Rent(int rentID, double rent_amount, String rent_paymentStatus, Date rent_Due) {
		super();
		this.rentID = rentID;
		this.rent_amount = rent_amount;
		this.rent_paymentStatus = rent_paymentStatus;
		this.rent_Due = rent_Due;
	}
    public int getRentID() {
		return rentID;
	}

	public void setRentID(int rentID) {
		this.rentID = rentID;
	}

	public double getRent_amount() {
		return rent_amount;
	}

	public void setRent_amount(double rent_amount) {
		this.rent_amount = rent_amount;
	}

	public String getRent_paymentStatus() {
		return rent_paymentStatus;
	}

	public void setRent_paymentStatus(String rent_paymentStatus) {
		this.rent_paymentStatus = rent_paymentStatus;
	}

	public Date getRent_Due() {
		return rent_Due;
	}

	public void setRent_Due(Date rent_Due) {
		this.rent_Due = rent_Due;
	}
}

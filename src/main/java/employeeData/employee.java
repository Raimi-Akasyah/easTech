package employeeData;

import java.util.Date;

public class employee {

	private int employeeID;
	private String emp_name;
	private String emp_email;
	private String emp_password;
	private String emp_phoneNum;
	private Date emp_dob;

	public employee(int employeeID, String emp_name, String emp_email, String emp_password, String emp_phoneNum,
			Date emp_dob) {
		super();
		this.employeeID = employeeID;
		this.emp_name = emp_name;
		this.emp_email = emp_email;
		this.emp_password = emp_password;
		this.emp_phoneNum = emp_phoneNum;
		this.emp_dob = emp_dob;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public void setEmp_phoneNum(String emp_phoneNum) {
		this.emp_phoneNum = emp_phoneNum;
	}

	public void setEmp_dob(Date emp_dob) {
		this.emp_dob = emp_dob;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public String getEmp_phoneNum() {
		return emp_phoneNum;
	}

	public Date getEmp_dob() {
		return emp_dob;
	}

	
}

package booking;

import residentData.Customer;
import residentData.CustomerDAO;
import residentData.Room;
import residentData.RoomDAO;
import rent.Rent;
import rent.RentDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResidentService {

    private CustomerDAO customerDAO;
    private RoomDAO roomDAO;
    private RentDAO rentDAO;

    public ResidentService() {
        this.customerDAO = new CustomerDAO();
        this.roomDAO = new RoomDAO();
        this.rentDAO = new RentDAO();
    }

    public List<residentInfo> getResidentDetailsForUser(String email) throws SQLException {
        List<residentInfo> residentInfoList = new ArrayList<>();
        
        // Get the customer by email
        Customer customer = customerDAO.getCustomerByEmail(email);
        if (customer != null) {
            Room room = roomDAO.getRoomByNumber(customer.getRoom());  // Get room details
            Rent rent = rentDAO.getRentById(customer.getId());  // Get rent details

            // Fetch rentAmount and handle case if Rent is null
            String rentAmount = rent != null ? String.valueOf(rent.getRent_amount()) : "N/A";

            residentInfo residentInfo = new residentInfo(
                customer.getFullName(),
                customer.getNoIC(),
                room != null ? room.getRoomType() : "N/A",
                room != null ? room.getRoomNumber() : "N/A",
                room != null ? room.getRoomBlock() : "N/A",
                room != null ? room.getAvailable() : "N/A",
                rentAmount,  // Add rentAmount
                rent != null ? rent.getRent_paymentStatus() : "N/A",
                rent != null ? rent.getRent_Due() : null
            );

            residentInfoList.add(residentInfo);
        }

        return residentInfoList;
    }
}


package Entity;

/**
 * Created by phimau on 11/18/2016.
 */

public class Invoice {
    private long date;
    private String customerID;
    private  String employeeID;

    public Invoice(String customerID, long date, String employeeID) {
        this.customerID = customerID;
        this.date = date;
        this.employeeID = employeeID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}

package Entity;

/**
 * Created by phimau on 11/18/2016.
 */

public class Custumer {
    private String id;
    private String name;
    private String numPhone;
    private String address;

    public Custumer(String address, String id, String name, String numPhone) {
        this.address = address;
        this.id = id;
        this.name = name;
        this.numPhone = numPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }
}

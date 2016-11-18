package Entity;

/**
 * Created by phimau on 11/18/2016.
 */

public class InvoiceDetail  {
    private String 	product_id;
    private int number;

    public InvoiceDetail(int number, String product_id) {
        this.number = number;
        this.product_id = product_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}

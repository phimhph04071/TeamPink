package Entity;

/**
 * Created by phimau on 11/18/2016.
 */

public class InvoiceDetail  {
    private Product 	product_id;
    private int number;

    public InvoiceDetail(int number, Product product_id) {
        this.number = number;
        this.product_id = product_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Product getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
    }
}

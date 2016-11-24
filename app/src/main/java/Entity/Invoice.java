package Entity;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by phimau on 11/18/2016.
 */

public class Invoice {
    private long date;
    private String customerID;
    private  String employeeID;
    private ArrayList<InvoiceDetail> listProduct;

    public Invoice(String customerID, String employeeID) {
        this.customerID = customerID;
        this.date = getTimeinMilisecon();
        this.employeeID = employeeID;
        this.listProduct = new ArrayList<InvoiceDetail>();
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
    public static long getTimeinMilisecon(){
        return  System.currentTimeMillis();
    }

    public ArrayList<InvoiceDetail> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<InvoiceDetail> listProduct) {
        this.listProduct = listProduct;
    }
    public void addProduct(InvoiceDetail invoiceDetail){
        this.listProduct.add(invoiceDetail);
    }
//    public JsonObject makeJsonObject(){
//        JsonObject object = new
//        JsonElement jsonElement = new JsonObject();
//
//        }
//        object.add("listProduct",new InvoiceDetail(1,"ssss"));
//        return object;
//    }
    public String maketStringJson(){
        String product=pasreToString();
        String result="{\"date\":\""+date+"\",\"customerID\":\""+customerID+"\",\"employeeID\":\""+employeeID+"\","+product+"}";
        Log.e("JSON",result);
        return result;
    }
    String pasreToString(){
        String result="\"listProducts\":[";
        for (int i=0;i<listProduct.size();i++){
            result+="{\"product_id\":\""+listProduct.get(i).getProduct_id().getId()+"\",\"number\":\""+listProduct.get(i).getNumber()+"\"}";
            if (i!=listProduct.size()-1){
                result+=",";
            }
        }
        result+="]";
        return result;
    }
    public void clearall(){
        listProduct.clear();
    }
}

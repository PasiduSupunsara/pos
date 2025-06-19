
package gui;

import java.util.Date;

public class GRNItem {

    
    public double getSellingPrice() {
        return sellingPrice;
    }

    
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    
    public Date getMfd() {
        return mfd;
    }

    
    public void setMfd(Date mfd) {
        this.mfd = mfd;
    }

    
    public Date getExp() {
        return exp;
    }

   
    public void setExp(Date exp) {
        this.exp = exp;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName(String text) {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory(String text) {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    
    
    private String productId;
    private String productName;
    private String category;
    private double qty;
    private double buyingPrice;
    private double sellingPrice;
    private Date mfd;
    private Date exp;

    
}

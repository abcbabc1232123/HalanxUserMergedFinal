

        package com.halanx.tript.userapp.POJO;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class ProductInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("ProductImage")
    @Expose
    private String productImage;
    @SerializedName("Features")
    @Expose
    private String features;
    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("StoreId")
    @Expose
    private List<Integer> storeId = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Integer> getStoreId() {
        return storeId;
    }

    public void setStoreId(List<Integer> storeId) {
        this.storeId = storeId;
    }

}
package model;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private String pnum;
    private String name;
    private String price;
    private String brand;
    private String model;
    private String specs;

    public String getPnum() { return pnum; }
    public void setPnum(String pnum) { this.pnum = pnum; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getSpecs() { return specs; }
    public void setSpecs(String specs) { this.specs = specs; }

    @Override
    public String toString() {
        return "Task{" + "pnum=" + pnum + ", name=" + name + ", price=" + price
                + ", brand=" + brand + ", model=" + model + ", specs=" + specs + '}';
    }
}

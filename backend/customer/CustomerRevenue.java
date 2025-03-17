package vn.devpro.fashionshop.backend.customer;

public class CustomerRevenue {
    private int customerId;
    private double revenue;

    public CustomerRevenue(int customerId, double revenue) {
        this.customerId = customerId;
        this.revenue = revenue;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}

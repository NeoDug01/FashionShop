package vn.devpro.fashionshop.backend.order;

import java.util.ArrayList;

import vn.devpro.fashionshop.backend.customer.CustomerManagement;
import vn.devpro.fashionshop.frontend.CartProduct;

public class Order {
	private int id;
    private int customerId;
    private String code;
    private ArrayList<CartProduct> orderProducts = new ArrayList<>();
    private double totalPrice;
    private boolean isPaid;

    public Order(int id,String code ,int customerId, ArrayList<CartProduct> orderProducts) {
        this.id = id;
        this.customerId = customerId;
        this.orderProducts = orderProducts;
        this.code=code;
        this.isPaid = false;
    }

    public Order() {
		super();
	}
    
    public void calculateTotalPrice() {
        this.totalPrice = 0;
        for (CartProduct product : orderProducts) {
            totalPrice += product.totalPrice(); // Cộng dồn giá trị từng sản phẩm
        }
    }
    
    public void display() {
        System.out.println("\n\t\tHOA DON");
        System.out.println("Mã đơn hàng: " + this.code);
        System.out.println("Khách hàng: " + CustomerManagement.getCustomerNameById(this.customerId));
        System.out.printf("%-30s %8s %15s %15s%n","Tên sản phẩm", "Số lượng", "�?ơn giá", "Thành ti�?n");
        for (CartProduct cp : orderProducts) {
            cp.display();
        }
        calculateTotalPrice();
		System.out.printf("Cong thanh tien: %,.2f%n",totalPrice);
    }
    
    public void markAsPaid() {
        this.isPaid = true;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }
    public ArrayList<CartProduct> getOrderProducts() {
        return orderProducts;
    }
    
    public void setOrderProducts(ArrayList<CartProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
}


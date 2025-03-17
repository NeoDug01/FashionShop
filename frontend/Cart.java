package vn.devpro.fashionshop.frontend;

import java.util.ArrayList;

import vn.devpro.fashionshop.backend.customer.CustomerManagement;

public class Cart {
	private int id;
	private int customerId;
	private ArrayList<CartProduct> cartProducts = new ArrayList<>();
	
	public void display() {
		System.out.println("\n\t\tGIO HANG CUA BAN");
		System.out.println("Khach hang: "+CustomerManagement.getCustomerNameById(this.customerId));
		System.out.println("SDT khach hang: "+CustomerManagement.findCustomerById(customerId).getMobile());	
		System.out.println("\tDanh sach hang hoa");
		System.out.printf("%-30s %8s %15s %15s%n","Ten san pham","So luong","Don gia","Thanh tien");
		double totalCartPrice = 0;
		for(CartProduct cp: cartProducts) {
			cp.display();
			totalCartPrice+=cp.totalPrice();
		}
		System.out.printf("Cong thanh tien: %,.2f%n",totalCartPrice);
	}
	public Cart() {
		super();
	}
	public Cart(int id, int customerId, ArrayList<CartProduct> cartProducts) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.cartProducts = cartProducts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public ArrayList<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(ArrayList<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public int findProductById(int id) {
		for(int index=0;index<cartProducts.size();index++) {
			if(cartProducts.get(index).getId()==id)
				return index;
		}
		return -1;
	}
	
}

package vn.devpro.fashionshop.backend.order;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import vn.devpro.fashionshop.backend.customer.CustomerManagement;
import vn.devpro.fashionshop.backend.customer.CustomerRevenue;
import vn.devpro.fashionshop.backend.product.ProductManagement;
import vn.devpro.fashionshop.backend.product.ProductRevenue;
import vn.devpro.fashionshop.frontend.CartProduct;

public class OrderManagement {
	public static Scanner sc = new Scanner(System.in);
    private static ArrayList<Order> orders = new ArrayList<>();

    // Lấy danh sách hóa đơn 
    public static ArrayList<Order> getOrders() {
        return orders;
    }
    // Them moi vao danh sach hoa don   
    public static void addOrder(Order order) {
        orders.add(order);
        System.out.println("\tHóa đơn đã được lưu thành công vào hệ thống.");
    }
    public static void management() {
		do {
			System.out.println("\n\t\tQUAN LI THONG KE");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Xem thong tin danh sach hoa don");
			System.out.println("\t2. Xoa hoa don");
			System.out.println("\t3. Xem tong doanh thu");
			System.out.println("\t4. Xem doanh thu theo khach hang");
			System.out.println("\t5. Xem doanh thu theo san pham");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				display();
				break;
			case 2:
				System.out.println("Nhap ma hoa don can xoa: ");
				String code =sc.nextLine();
				deleteOrder(code);
				break;
			case 3:
				calculateTotalRevenue();
				break;
			case 4:
				calculateRevenueByCustomer();
				break;
			case 5:
				calculateRevenueByProduct();
				break;
			case 0:
				return;
			default:
				System.out.println("Lựa chon không hợp lệ. Vui lòng chon lại.");
				break;
			}
		} while (true);
	}
    
    // Hiển thị danh sách hóa đơn
    public static void display() {
        if (orders.isEmpty()) {
            System.out.println("\tChưa có hóa đơn nào được lưu.");
            return;
        }
        System.out.println("\n\t\tDANH SACH HÓA DƠN");
        for (Order order : orders) {
            order.display();
        }
    }

    // Xóa hóa đơn
    public static void deleteOrder(String orderCode) {
        for (Order order : orders) {
            if (order.getCode().equalsIgnoreCase(orderCode)) {
                orders.remove(order);
                System.out.println("\tDã xóa hóa đơn thành công!");
                return;
            }
        }
        System.out.println("\tKhông tìm thấy hóa đơn với mã: " + orderCode);
    }

    // Tính tổng doanh thu
    public static void calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : orders) {
            if (order.isPaid()) {
                totalRevenue += order.getTotalPrice();
            }
        }
        System.out.printf("\n\tTổng doanh thu: %,.2f VND%n", totalRevenue);
    }

    // Tính doanh thu theo khách hàng
    public static void calculateRevenueByCustomer() {
        // Tạo một danh sách để lưu trữ doanh thu theo khách hàng
        ArrayList<CustomerRevenue> customerRevenues = new ArrayList<>();

        for (Order order : orders) {
            if (order.isPaid()) {
                int customerId = order.getCustomerId();
                double orderTotalPrice = order.getTotalPrice();
                boolean found = false;

                // Tìm khách hàng trong danh sách và cập nhật doanh thu
                for (CustomerRevenue customerRevenue : customerRevenues) {
                    if (customerRevenue.getCustomerId() == customerId) {
                        customerRevenue.setRevenue(customerRevenue.getRevenue() + orderTotalPrice);
                        found = true;
                        break;
                    }
                }

                // Nếu khách hàng chưa có trong danh sách, thêm khách hàng mới
                if (!found) {
                    customerRevenues.add(new CustomerRevenue(customerId, orderTotalPrice));
                }
            }
        }


        System.out.println("\n\tTỔNG DOANH THU THEO KHACH HÀNG");
        for (CustomerRevenue customerRevenue : customerRevenues) {
            String customerName = CustomerManagement.getCustomerNameById(customerRevenue.getCustomerId());
            System.out.printf("%-30s: %,.2f VND%n", customerName, customerRevenue.getRevenue());
        }
    }


    // Tính doanh thu và số lượng bán theo sản phẩm
    public static void calculateRevenueByProduct() {
        ArrayList<ProductRevenue> productRevenues = new ArrayList<>(); 

        for (Order order : orders) {
            if (order.isPaid()) {  
                for (CartProduct cartProduct : order.getOrderProducts()) {
                    int productId = cartProduct.getId();
                    String productName = ProductManagement.getProductById(productId).getName(); 
                    int quantity = cartProduct.getQuantity();
                    double revenue = cartProduct.totalPrice();

                    boolean found = false;

                    // Tìm xem sản phẩm đã có trong danh sách chưa
                    for (ProductRevenue productRevenue : productRevenues) {
                        if (productRevenue.getProductId() == productId) {
                            productRevenue.setQuantitySold(productRevenue.getQuantitySold() + quantity); 
                            productRevenue.setRevenue(productRevenue.getRevenue() + revenue);  
                            found = true;
                            break;
                        }
                    }

                    // Nếu sản phẩm chưa có trong danh sách, thêm mới
                    if (!found) {
                        productRevenues.add(new ProductRevenue(productId, productName, quantity, revenue));
                    }
                }
            }
        }

        // In doanh thu theo sản phẩm
        System.out.println("\n\tDOANH THU THEO SẢN PHẨM");
        System.out.printf("%-5s %-30s %-20s %-15s%n", "STT", "Tên sản phẩm", "Tổng số lượng đã bán", "Doanh thu");
        int index = 1;
        for (ProductRevenue productRevenue : productRevenues) {
            System.out.printf("%-5d %-30s %-20d %,15.2f VND%n", index++, productRevenue.getProductName(), productRevenue.getQuantitySold(), productRevenue.getRevenue());
        }
    }

}

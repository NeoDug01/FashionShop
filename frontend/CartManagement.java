package vn.devpro.fashionshop.frontend;

import java.util.ArrayList;
import java.util.Scanner;

import vn.devpro.fashionshop.backend.customer.Customer;
import vn.devpro.fashionshop.backend.customer.CustomerManagement;
import vn.devpro.fashionshop.backend.order.Order;
import vn.devpro.fashionshop.backend.order.OrderManagement;
import vn.devpro.fashionshop.backend.product.ProductManagement;

public class CartManagement {

	public static Cart cart = new Cart();
	public static Scanner sc = new Scanner(System.in);
	public static int autoId = 1;
	public static void management() {
		do {
			System.out.println("\n\t\tCAP NHAT THONG TIN GIO HANG");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Xem thong tin san pham");
			System.out.println("\t2. Xem thong tin gio hang");
			System.out.println("\t3. Them san pham vao gio hang");
			System.out.println("\t4. Sua thong tin san pham trong gio hang");
			System.out.println("\t5. Xoa san pham khoi gio hang");
			System.out.println("\t6. Xoa toan bo gio hang");
			System.out.println("\t7. Thanh toan gio hang");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				ProductManagement.display();
				break;
			case 2:
				cart.display();
				break;
			case 3:
				add();
				break;
			case 4:
				edit();
				break;
			case 5:
				remove();
				break;
			case 6:
				removeAll();
				break;
			case 7:
				checkOut();
				break;
			case 0:
				return;
			default:
				System.out.println("Lựa chon không hợp lệ. Vui lòng chon lại.");
				break;
			}
		} while (true);
	}

	private static void add() {
		System.out.println("\n\t\tTHEM SAN PHAM VAO GIO HANG");
		System.out.print("\tNhap ma SP: ");
		String code = sc.nextLine();
		int index = ProductManagement.findProductByCode(code);
		if (index == -1) {
			System.out.println("\tMa san pham khong dung. Xin vui long nhap lai!");
			return;
		}
		System.out.print("\tNhap so luong can mua: ");
		int quantity = Integer.parseInt(sc.nextLine());
		if (quantity < 1) {
			System.out.println("\tSo luong khong hop le!");
			return;
		}

//		Tim san pham trong gio hang
		int index1 = cart.findProductById(ProductManagement.getProducts().get(index).getId());
		if (index1 > -1) {
			cart.getCartProducts().get(index1).updateQuantity(quantity);
		} else {
			cart.getCartProducts().add(new CartProduct(ProductManagement.getProducts().get(index).getId(), quantity));
		}
		System.out.println("\tDa them san pham vao gio hang!");
	}

	private static void edit() {
		System.out.println("\n\t\tSUA SAN PHAM TRONG GIO HANG");
		System.out.print("\tNhap ma SP: ");
		String code = sc.nextLine();
		int index = ProductManagement.findProductByCode(code);
		if (index == -1) {
			System.out.println("\tMa san pham khong dung. Xin vui long nhap lai!");
			return;
		}
//		Lay id cua san pham
		int productId = ProductManagement.getProducts().get(index).getId();
//		Kiem tra san pham trong gio hang
		int index1 = cart.findProductById(productId);
		if (index1 == -1) {
			System.out.println("\tSan pham khong co trong gio hang!");
			return;
		} else {
			System.out.println("\tNhap vao so luong moi: ");
			int quantity = Integer.parseInt(sc.nextLine());
			if (quantity < 1) {
				System.out.println("\tSo luong khong hop le!");
				return;
			}
			cart.getCartProducts().get(index1).setQuantity(quantity);
			System.out.println("\tSua so luong thanh cong!");
		}
	}

	private static void remove() {
		System.out.println("\n\t\tXOA SAN PHAM TRONG GIO HANG");
		System.out.print("\tNhap ma SP: ");
		String code = sc.nextLine();
		int index = ProductManagement.findProductByCode(code);
		if (index == -1) {
			System.out.println("\tMa san pham khong dung. Xin vui long nhap lai!");
			return;
		}
//		Lay id cua san pham
		int productId = ProductManagement.getProducts().get(index).getId();
//		Kiem tra san pham trong gio hang
		int index1 = cart.findProductById(productId);
		if (index1 == -1) {
			System.out.println("\tSan pham khong co trong gio hang!");
			return;
		} else {
			cart.getCartProducts().remove(index1);
			System.out.println("\tXoa san pham trong gio hang thanh cong!");
		}

	}

	private static void removeAll() {
		cart = new Cart();
		System.out.println("\tXoa gio hang thanh cong!");
	}

	private static void checkOut() {
//		Cap nhat thong tin ve khach hang
		Customer customer=new Customer();
		System.out.print("\tNhap ma khach hang: ");
		String customerCode= sc.nextLine();
		int index = CustomerManagement.findCustomerByCode(customerCode);
		if(index==-1) {
//			Thong tin khach hang chua co trong he thong
			System.out.print("\tNhap ten khach hang: ");
			String name= sc.nextLine();
			if(name==null||name.trim().length()<=0) {
				System.out.println("\tTen khach hang khong duoc de trong");
				return;
			}
			System.out.print("\tNhap SDT khach hang: ");
			String mobile=sc.nextLine();
			customerCode = ""+(100+CustomerManagement.getAutoId());
			customer = new Customer(CustomerManagement.getAutoId(), 
								customerCode, name,mobile);
			CustomerManagement.increaseAutoId();
			CustomerManagement.getCustomers().add(customer);
		}else {
//			Khach hang co trong he thong
			customer= CustomerManagement.getCustomers().get(index);
		}
		cart.setCustomerId(customer.getId());
		// H�?i xác nhận thanh toán
	    System.out.print("\tBạn có muốn thanh toán gio hàng này không? (yes/no): ");
	    String confirm = sc.nextLine().trim().toLowerCase();

	    if (confirm.equalsIgnoreCase("yes")||confirm.equalsIgnoreCase("y")) {
	        // Tạo mã hóa đơn
	        String orderCode = "ORD" + autoId++;
	        
	        // Tạo hóa đơn
	        Order order = new Order(autoId, orderCode, customer.getId(), cart.getCartProducts());
	        order.markAsPaid(); // �?ánh dấu đã thanh toán
	        OrderManagement.addOrder(order);
	        // Thông báo và xóa gi�? hàng
	        System.out.println("\tDặt hàng thành công! Hóa đơn đã được lưu.");
	        cart = new Cart();
	    } else {
	        // Không thanh toán, hủy gi�? hàng
	        System.out.println("\tHóa đơn chưa được thanh toán. Gio hàng không được lưu.");
	    }
	}
}



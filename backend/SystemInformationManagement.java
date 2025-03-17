package vn.devpro.fashionshop.backend;

import java.util.Scanner;

import vn.devpro.fashionshop.backend.category.CategoryManagement;
import vn.devpro.fashionshop.backend.customer.CustomerManagement;
import vn.devpro.fashionshop.backend.order.OrderManagement;
import vn.devpro.fashionshop.backend.product.ProductManagement;
import vn.devpro.fashionshop.frontend.Cart;
import vn.devpro.fashionshop.frontend.CartManagement;


public class SystemInformationManagement {
	public static Scanner sc = new Scanner(System.in);
	public static void management() {
		do {
			System.out.println("\n\t\tCAP NHAT THONG TIN HE THONG");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Cap nhat thong tin loai san pham");
			System.out.println("\t2. Cap nhat thong tin san pham");
			System.out.println("\t3. Cap nhat thong tin khach hang");
			System.out.println("\t4. Quan li thong ke");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				CategoryManagement.management();
				break;
			case 2:
				ProductManagement.management();
				break;
			case 3:
				CustomerManagement.management();
				break;
			case 4:
				OrderManagement.management();
				break;
			case 0:
				return;
			default:
				System.out.println("Lựa chon không hợp lệ. Vui lòng chon lại.");
				break;
			}
		} while (true);
	}

	
}

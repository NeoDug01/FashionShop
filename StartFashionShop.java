package vn.devpro.fashionshop;

import java.util.Scanner;

import vn.devpro.fashionshop.backend.SystemInformationManagement;
import vn.devpro.fashionshop.backend.category.CategoryManagement;
import vn.devpro.fashionshop.backend.customer.CustomerManagement;
import vn.devpro.fashionshop.backend.product.ProductManagement;
import vn.devpro.fashionshop.frontend.CartManagement;


public class StartFashionShop {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		CategoryManagement.init();
		CustomerManagement.init();
		ProductManagement.init();
        do {
            System.out.println("\n\t\tCHUONG TRINH QUAN LY CUA HANG");
            System.out.println("Hay chon mot bai toan can giai quyet");
            System.out.println("\t1. Danh cho nguoi quan ly - backend");
            System.out.println("\t2. Danh cho khach hang - frontend");
            System.out.println("\t0. Thoat khoi chuong trinh");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                	SystemInformationManagement.management();
                    break;
                case 2:
                    CartManagement.management();
                    break;
                case 0:
                    System.out.println("Dã thoát khoi chương trình.");
                	System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chon không hợp lệ. Vui lòng chon lại.");
                    break;
            }
        } while (true);

	}

}

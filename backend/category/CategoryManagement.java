package vn.devpro.fashionshop.backend.category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class CategoryManagement {
	public static Scanner sc = new Scanner(System.in);
	
	public static int autoId =1;
	
	private static ArrayList<Category> categories = new ArrayList<>();
	
	
	public static ArrayList<Category> getCategories() {
		return categories;
	}
	public static void setCategories(ArrayList<Category> categories) {
		CategoryManagement.categories = categories;
	}
	public static void init() {
		categories.add(new Category(autoId++, "101", "Ao"));
		categories.add(new Category(autoId++, "102", "Quan"));
		categories.add(new Category(autoId++, "103", "Vay"));
		categories.add(new Category(autoId++, "104", "Áo sơ mi"));
		categories.add(new Category(autoId++, "105", "Giày thể thao"));
		categories.add(new Category(autoId++, "106", "Mũ"));
		categories.add(new Category(autoId++, "107", "Túi xách"));
		categories.add(new Category(autoId++, "108", "Đồng hồ"));
		categories.add(new Category(autoId++, "109", "Kính mát"));
		categories.add(new Category(autoId++, "110", "Dép"));
	}
	public static void management() {
		
		do {
			System.out.println("\n\t\tCAP NHAT THONG TIN LOAI SAN PHAM");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Xem thong tin loai san pham");
			System.out.println("\t2. Them thong tin loai san pham");
			System.out.println("\t3. Sua thong tin loai san pham");
			System.out.println("\t4. Xoa thong tin loai san pham");
			System.out.println("\t5. Sap xep theo ten loai san pham");		
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			try {
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					display();
					break;
				case 2:
					add();
					break;
				case 3:
					edit();
					break;
				case 4:
					delete();
					break;
				case 5:
					sortByName(categories);
					break;
				case 0:
					return;
				default:
					System.out.println("Lựa chon không hợp lệ. Vui lòng chon lại.");
					break;
				}
			}catch(NumberFormatException e) {
				System.out.print("Vui long nhap so hop le");			
			}
		} while (true);
	}
	private static void sortByName(ArrayList<Category> categories) {
	    
	    categories.sort(new Comparator<Category>() {
	        @Override
	        public int compare(Category c1, Category c2) {
	            return c1.getName().compareToIgnoreCase(c2.getName());
	        }
	    });

	    System.out.println("Danh sách nhà cung cấp đã được sắp xếp theo tên:");
	    display(); 
	}
	private static void delete() {
		System.out.println("\n\t\tXOA THONG TIN LOAI SAN PHAM");
		System.out.print("\tNhap ma(code) loai san pham: ");
		String code = sc.nextLine();
		int index = findCategoryByCode(code);
		categories.remove(index);
		System.out.print("Da xoa loai san pham thanh cong!");
	}
	private static void edit() {
		System.out.println("\n\t\tSUA THONG TIN LOAI SAN PHAM");
		System.out.print("\tNhap ma(code) loai san pham: ");
		String code = sc.nextLine();
		int index = findCategoryByCode(code);
		if(index==-1) {
			System.out.println("\tloai san pham khong co trong danh sach");
			return;
		}
		categories.get(index).edit();
	}
	private static void add() {
		System.out.print("Nhap code loai san pham: ");
		String code = sc.nextLine();
		System.out.print("Nhap ten loai san pham: ");
		String name = sc.nextLine();
		if(name.trim().length()<=0) {
			System.out.println("\tTen khong duoc bo trong");
			return;
		}
		categories.add(new Category(autoId, code, name));
		System.out.print("Da them loai san pham!");
	}
	private static void display() {
		System.out.println("\n\t\tDANH SACH LOAI SAN PHAM");
		System.out.printf("%3s %12s %-60s%n","ID","Ma NCC","Ten NCC");
		for( Category category:categories ) {
			category.display();
		}
		
	}
	public static int findCategoryByCode(String code) {
		for(int index=0;index<categories.size();index++) {
			if(categories.get(index).getCode().trim().equals(code.trim())) 
				return index;
		}
		return -1;
	}
	public static String getCategoryNameById(int id) {
		for(Category category: categories) {
			if(category.getId()==id) {
				return category.getName();
			}
		}
		return null;
	}
}

package vn.devpro.fashionshop.backend.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.fashionshop.backend.category.Category;
import vn.devpro.fashionshop.backend.category.CategoryManagement;

public class ProductManagement {
	public static int autoId =1;
	public static Scanner sc = new Scanner(System.in);
	
	private static ArrayList<Product> products = new ArrayList<>();
	
	public static void init() {
		products.add(new Product(autoId++, 1, "101", "Ao phong", 450000));
		products.add(new Product(autoId++, 2, "102", "Quan sooc", 40000));
		products.add(new Product(autoId++, 3, "103", "Vay cong chua", 1250000));
		products.add(new Product(autoId++, 1, "104", "Ao bong da", 250000));
		products.add(new Product(autoId++, 3, "105", "Vay body", 950000));
		products.add(new Product(autoId++, 4, "106", "Áo sơ mi nam", 550000));     
		products.add(new Product(autoId++, 5, "107", "Giày thể thao Nike", 1200000)); 
		products.add(new Product(autoId++, 6, "108", "Mũ lưỡi trai Adidas", 350000));  
		products.add(new Product(autoId++, 7, "109", "Túi xách nữ", 800000));        
		products.add(new Product(autoId++, 8, "110", "Đồng hồ Casio", 1500000));       
		products.add(new Product(autoId++, 9, "111", "Kính mát Ray-Ban", 1200000));    
		products.add(new Product(autoId++, 10, "112", "Dép sandal nam", 250000));    
		products.add(new Product(autoId++, 4, "113", "Áo sơ mi nữ", 450000));        
		products.add(new Product(autoId++, 5, "114", "Giày thể thao Puma", 1000000)); 
		products.add(new Product(autoId++, 6, "115", "Mũ bảo hiểm", 250000));         
		products.add(new Product(autoId++, 7, "116", "Túi xách da thật", 1500000));   
		products.add(new Product(autoId++, 8, "117", "Đồng hồ Rolex", 25000000));     
		products.add(new Product(autoId++, 9, "118", "Kính mát Prada", 2000000));    
		products.add(new Product(autoId++, 10, "119", "Dép crocs", 500000));        
	}
	public static void management() {
		do {
			System.out.println("\n\t\tCAP NHAT THONG TIN SAN PHAM");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Xem thong tin san pham");
			System.out.println("\t2. Them thong tin san pham");
			System.out.println("\t3. Sua thong tin san pham");
			System.out.println("\t4. Xoa thong tin san pham");
			System.out.println("\t5. Sap xep theo ten san pham");
			System.out.println("\t6. Tim kiem theo ten san pham");
			System.out.println("\t7. Tim kiem theo loai san pham");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
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
				sortByName(products);
			case 6:
				searchByName();
				break;
			case 7:
				searchByCategory();
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le. Vui long chon lai.");
				break;
			}
		} while (true);
	}
	
//	Tim kiem bang ten
	public static void searchByName() {
	    System.out.print("\n\tNhap ten (hoac mot phan ten) san pham can tim: ");
	    String keyword = sc.nextLine().toLowerCase();
	    System.out.println("\n\t\tDANH SACH SAN PHAM PHU HOP: '"+keyword.toUpperCase()+"'");

	    boolean found = false;
	    for (Product product : products) {
	        if (product.getName().toLowerCase().contains(keyword)) {
	            product.display();
	            found = true;
	        }
	    }
	    if (!found) {
	        System.out.println("\tKhong co san pham phu hop.");
	    }
	}
//	Tim kiem bang loai san pham	
	public static void searchByCategory() {
		do {
			System.out.println("\n\t\tTIM KIEM SAN PHAM THEO LOAI SAN PHAM");
			System.out.println("Hay chon mot chuc nang ");
			System.out.println("\t1. Tim kiem bang ma(code) loai san pham ");
			System.out.println("\t2. Tim kiem bang ten loai san pham");			
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				searchByCategoryCode();
				break;
			case 2:
				searchByCategoryName();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le. Vui long chon lai.");
				break;
			}
		} while (true);
	}
	
// 	Tim kiem bang ma loai san pham
	public static void searchByCategoryCode() {
	    System.out.print("\n\tNhap ma chung loai (CategoryCode) can tim: ");
	    String keyword = sc.nextLine().toLowerCase();

	   
	    int categoryIndex = CategoryManagement.findCategoryByCode(keyword);
	    if (categoryIndex == -1) {
	        System.out.println("\tKhong tim thay chung loai voi ma da nhap.");
	        return;
	    }

	 
	    int categoryId = CategoryManagement.getCategories().get(categoryIndex).getId();

	    System.out.println("\n\t\tDANH SACH SAN PHAM PHU HOP THEO CHUNG LOAI "+CategoryManagement.getCategories().get(categoryIndex).getName().toUpperCase());
	    boolean found = false;

	    
	    for (Product product : products) {
	        if (product.getCategoryId() == categoryId) {
	            product.display();
	            found = true;
	        }
	    }

	    if (!found) {
	        System.out.println("\tKhong co san pham phu hop voi ma chung loai.");
	    }
	}
// 	Tim kiem bang ten loai san pham
	public static void searchByCategoryName() {
	    System.out.print("\n\tNhap ten chung loai (hoac mot phan ten): ");
	    String keyword = sc.nextLine().toLowerCase();

	    System.out.println("\n\t\tDANH SACH SAN PHAM PHU HOP THEO CHUNG LOAI");

	    boolean found = false;

	    
	    for (Category category : CategoryManagement.getCategories()) {
	        if (category.getName().toLowerCase().contains(keyword)) {
	            int categoryId = category.getId();

	            for (Product product : products) {
	                if (product.getCategoryId() == categoryId) {
	                    product.display();
	                    found = true;
	                }
	            }
	        }
	    }

	    if (!found) {
	        System.out.println("\tKhong co san pham phu hop voi ten chung loai.");
	    }
	}

//	Sap xep theo ten
	private static void sortByName(ArrayList<Product> products) {
	   
	    products.sort(new Comparator<Product>() {
	        @Override
	        public int compare(Product c1, Product c2) {
	            return c1.getName().compareToIgnoreCase(c2.getName());
	        }
	    });

	    System.out.println("Danh sach san pham sap xep theo ten:");
	    display(); 
	}
	
	private static void delete() {
		System.out.println("\n\t\tXOA THONG TIN SAN PHAM");
		System.out.print("\tNhap ma(code) san pham: ");
		String code = sc.nextLine();
		int index = findProductByCode(code);
		products.remove(index);
		System.out.print("Da xoa san pham thanh cong!");		
	}
	
	private static void edit() {
		System.out.println("\n\t\tSUA THONG TIN SAN PHAM");
		System.out.print("\tNhap ma(code) san pham: ");
		String code = sc.nextLine();
		int index = findProductByCode(code);
		if(index==-1) {
			System.out.println("\tSan pham khong co trong danh sach");
			return;
		}
		products.get(index).edit();
		
	}
	
	public static int findProductByCode(String code) {
		for(int index=0;index<products.size();index++) {
			if(products.get(index).getCode().trim().equals(code.trim())) 
				return index;
		}
		return -1;
	}
	
	private static void add() {
		System.out.println("\n\t\tTHEM SAN PHAM DANG BAN");
		System.out.print("\tChon nha cung cap(nhap code NCC): ");
		String providerCode= sc.nextLine();
		int providerIndex = CategoryManagement.findCategoryByCode(providerCode);
		if(providerIndex==-1) {
			System.out.println("\tNha cung cap chua co trong danh sach");
			return;
		}
		System.out.print("\tNhap ten san pham: ");
		String name = sc.nextLine();
		if(name == null || name.trim().length()<=0) {
			System.out.println("\tTen khong duoc de trong");
			return;
		}
		System.out.print("\tNhap gia san pham: ");
		double price = Double.parseDouble(sc.nextLine());
		if(price<0) {
			System.out.println("\tDon gia khong phu hop");
			return;
		}
		
		int providerId = CategoryManagement.getCategories().get(providerIndex).getId();
		String code = "";
		code = code+ (100*providerId+autoId);
		Product product = new Product(autoId++, providerId, code, name, price);
		products.add(product);
		System.out.println("\tThem thanh cong!");
	}
	public static void display() {
		System.out.println("\n\t\tDANH SACH SAN PHAM DANG BAN");
		System.out.printf("%3s %-25s %10s %-30s %15s%n","ID","Nha cung cap","Ma SP","Ten san pham","Don gia");
		for (Product product : products) {
			product.display();
		}
	}
	public static Product getProductById(int id) {
		for(Product product: products) {
			if(product.getId()==id) return product;
		}
		return new Product();
	}
	public static ArrayList<Product> getProducts() {
		return products;
	}
	public static void setProducts(ArrayList<Product> products) {
		ProductManagement.products = products;
	}
	
}

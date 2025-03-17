package vn.devpro.fashionshop.backend.product;

import java.util.Scanner;

import vn.devpro.fashionshop.backend.category.CategoryManagement;

public class Product {
	private int id;
	private int categoryId;
	private String code;
	private String name;
	private double price;
	
	public void display() {
		System.out.printf("%3d %-25s %10s %-30s %,15.2f%n",this.id,
				CategoryManagement.getCategoryNameById(this.categoryId),
				this.code,this.name,this.price);
	}
	public Product() {
		super();
	}
	public Product(int id, int categoryId, String code, String name, double price) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.code = code;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void edit() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n\t\tSUA THONG TIN HANG HOA");
			System.out.println("Hay chon mot bai toan can giai quyet");
			System.out.println("\t1. Sua ten hang hoa");
			System.out.println("\t2. Sua gia tien");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				System.out.print("\tNhap ten moi: ");
				String name = sc.nextLine();
				if(name == null || name.trim().length()<=0) {
					System.out.println("\tTen khong duoc de trong");
					return;
				}
				this.setName(name);
				System.out.println("\tSua ten thanh cong!");
				break;
			case 2:
				System.out.print("\tNhap gia tien moi: ");
				Double price = Double.parseDouble(sc.nextLine());
				if(price<0) {
					System.out.println("\tDon gia khong phu hop");
					return;
				}
				this.setPrice(price);
				System.out.println("\tSua gia tien thanh cong!");
				break;
			case 0:
				return; // Quay lai menu cha
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		} while (true);
	}
	
}

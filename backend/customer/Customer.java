package vn.devpro.fashionshop.backend.customer;

import java.util.Scanner;

public class Customer {
	private int id;
	private String code;
	private String name;
	private String mobile;
	public Customer() {
		super();
	}
	public Customer(int id, String code, String name, String mobile) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.mobile = mobile;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void display() {
		System.out.printf("%3d %12s %-60s %13s%n",this.id,this.code,this.name,this.mobile);
	}
	public void edit() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n\t\tSUA THONG TIN HANG HOA");
			System.out.println("Hay chon mot bai toan can giai quyet");
			System.out.println("\t1. Sua ten hang hoa");
			System.out.println("\t2. Sua SDT");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				System.out.print("\tNhap ten khach hang: ");
				String name = sc.nextLine();
				if(name.trim().length()<=0) {
					System.out.println("\tTen khong duoc bo trong");
					return;
				}
				this.name=name;
				System.out.println("\tSua ten thanh cong!");
				break;
			case 2:
				System.out.print("\tNhap SDT moi: ");
				String moblie=sc.nextLine();
				this.setMobile(moblie);
				System.out.println("\tSua SDT thanh cong!");
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

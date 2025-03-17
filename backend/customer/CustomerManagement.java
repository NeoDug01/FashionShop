package vn.devpro.fashionshop.backend.customer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class CustomerManagement {
	public static Scanner sc = new Scanner(System.in);
	
	public static int autoId =1;
	
	private static ArrayList<Customer> customers = new ArrayList<>();
	
	public static int getAutoId() {
		return autoId;
	}
	public static void increaseAutoId() {
		CustomerManagement.autoId++;
	}
	public static ArrayList<Customer> getCustomers() {
		return customers;
	}
	public static void setCustomers(ArrayList<Customer> customers) {
		CustomerManagement.customers = customers;
	}
	public static void init() {
		customers.add(new Customer(autoId++, "101", "Nguyen Van A","0123456789"));
		customers.add(new Customer(autoId++, "102", "Nguyen Thi B","0987654321"));
		customers.add(new Customer(autoId++, "103", "Tran A","0123456712"));
	}
	public static void management() {
		do {
			System.out.println("\n\t\tCAP NHAT THONG TIN KHACH HANG");
			System.out.println("Hay chon mot chuc nang quan ly");
			System.out.println("\t1. Xem thong tin khach hang");
			System.out.println("\t2. Them thong tin khach hang");
			System.out.println("\t3. Sua thong tin khach hang");
			System.out.println("\t4. Xoa thong tin khach hang");
			System.out.println("\t5. Sap xep theo ten khach hang");
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
				sortByName(customers);
			case 0:
				return;
			default:
				System.out.println("Lựa ch�?n không hợp lệ. Vui lòng ch�?n lại.");
				break;
			}
		} while (true);
	}
	private static void sortByName(ArrayList<Customer> customers) {
	    
	    customers.sort(new Comparator<Customer>() {
	        @Override
	        public int compare(Customer c1, Customer c2) {
	            // Lấy phần tên sau cùng trong fullname và so sánh
	            String lastName1 = c1.getName().split(" ")[c1.getName().split(" ").length - 1];
	            String lastName2 = c2.getName().split(" ")[c2.getName().split(" ").length - 1];
	            return lastName1.compareToIgnoreCase(lastName2); // So sánh tên
	        }
	    });
	    System.out.println("Danh sách khach hang đã được sắp xếp theo tên:");
	    display(); 
	}
	private static void delete() {
		System.out.println("\n\t\tXOA THONG TIN khach hang");
		System.out.print("\tNhap ma(code) khach hang: ");
		String code = sc.nextLine();
		int index = findCustomerByCode(code);
		customers.remove(index);
		System.out.print("Da xoa khach hang thanh cong!");
	}
	private static void edit() {
		System.out.println("\n\t\tSUA THONG TIN KHACH HANG");
		System.out.print("\tNhap ma(code) khach hang: ");
		String code = sc.nextLine();
		int index = findCustomerByCode(code);
		if(index==-1) {
			System.out.println("\tkhach hang khong co trong danh sach");
			return;
		}
		customers.get(index).edit();
	}
	private static void add() {
		System.out.print("Nhap code khach hang: ");
		String code = sc.nextLine();
		System.out.print("Nhap ten khach hang: ");
		String name = sc.nextLine();
		if(name.trim().length()<=0) {
			System.out.println("\tTen khong duoc bo trong");
			return;
		}
		System.out.print("Nhap SDT khach hang: ");
		String mobile=sc.nextLine();
		customers.add(new Customer(autoId, code, name,mobile));
		System.out.print("Da them khach hang!");
	}
	private static void display() {
		System.out.println("\n\t\tDANH SACH KHACH HANG");
		System.out.printf("%3s %12s %-60s %13s%n","ID","Ma KH","Ten KH","So dien thoai");
		for( Customer customer:customers ) {
			customer.display();
		}
		
	}
	public static int findCustomerByCode(String code) {
		for(int index=0;index<customers.size();index++) {
			if(customers.get(index).getCode().trim().equals(code.trim())) 
				return index;
		}
		return -1;
	}
	public static Customer findCustomerById(int id) {
		for(Customer c: customers) {
			if(c.getId()==id) return c;
		}
		return null;
	}
	public static String getCustomerNameById(int id) {
		for(Customer c: customers) {
			if(c.getId()==id) return c.getName();
		}
		return "";
	}
}

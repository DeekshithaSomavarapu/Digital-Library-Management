package com.example.digitallibrary;

import java.util.*;

public class DigitalLibraryManagement {

	static class Book {
		int id;
		String title;
		String author;
		boolean isIssued;
		
		public Book(int id, String title, String author) {
			this.id = id;
			this.title = title;
			this.author = author;
			this.isIssued = false;
		}
		
		@Override
		public String toString() {
			return "Book ID: " +id+ ", Title: " +title+ ", Author: " + author+ ", Issued:" + (isIssued? "Yes" : "No");
		}
	}
	
	private static Map<Integer, Book> books = new HashMap<>();
	private static Map<Integer, String> issuedBooks = new HashMap<>();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to DIgital Library Management System");
		seedBooks();
		
		while(true) {
			System.out.println("\nSelect an option: ");
			System.out.println("1. View Books\n2. Issue Book\n3. Return Books\n4. Add Book\n5. Exit");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1: 
				viewBooks();
				break;
			case 2:
				issueBook();
				break;
			case 3:
				returnBook();
				break;
			case 4:
				addBook();
				break;
			case 5:
				System.out.println("Thank you for using the Digital Library Management System.");
				return;
			default:
				System.out.println("Invalid option. Please try again");
			}
		}
	}

	private static void seedBooks() {
		books.put(1,  new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
		books.put(2,  new Book(2, "To Kill a Mockingbird", "Harper Lee"));
		books.put(3,  new Book(3, "1984", "George Orwell"));
	}
	
	private static void viewBooks() {
		System.out.println("\nAvailable Bookd: ");
		for(Book book : books.values()) {
			System.out.println(book);
		}
	}
	
	private static void issueBook() {
		System.out.println("Enter Book ID to issue:");
		int bookId = sc.nextInt();
		sc.nextLine();
		
		if(books.containsKey(bookId)) {
			Book book = books.get(bookId);
			if(!book.isIssued) {
				System.out.println("Enter your name:");
				String userName = sc.nextLine();
				book.isIssued = true;
				issuedBooks.put(bookId, userName);
				System.out.println("Book issued successfully to " +userName);
			}
			else {
				System.out.println("Book is already issued.");
			}
		}
		else {
			System.out.println("Invalid Book ID.");
		}
	}
	
	private static void returnBook() {
		System.out.println("Enter Book ID to return:");
		int bookId = sc.nextInt();
		sc.nextLine();
		
		if(books.containsKey(bookId)) {
			Book book = books.get(bookId);
			if(book.isIssued) {
				book.isIssued = false;
				String userName = issuedBooks.remove(bookId);
				System.out.println("Book returned successfully by" + userName);
			}
			else {
				System.out.println("This book was not issued.");
			}
		}
		else {
			System.out.println("Invalid Book ID.");
		}
	}
	
	private static void addBook() {
		System.out.println("Enter Book Title:");
		String title = sc.nextLine();
		
		System.out.println("Enter Book Author:");
		String author = sc.nextLine();
		
		int id = books.size() + 1;
		books.put(id,  new Book(id, title, author));
		System.out.println("Book added successfully with ID:" +id);
	}
}

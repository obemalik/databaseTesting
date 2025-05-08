package databaseTesting.databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
	private static final String Insert = null;

	WebDriver driver = new ChromeDriver();

	Connection con;

	Statement stmt;

	ResultSet rs;

	String website = "https://smartbuy-me.com/account/register";

	@BeforeTest
	public void mySetup() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "1234");

		driver.manage().window().maximize();
		driver.get(website);
	}

	@Test(priority = 1, enabled = true)
	public void AddNewCustomer() throws SQLException {

		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'Abc company', 'Ali', 'Ahmad', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00);";

		stmt = con.createStatement();

		int rowInserted = stmt.executeUpdate(query);

		System.out.println(rowInserted);

	}

	@Test(priority = 2, enabled = true)
	public void UpdateCustomerInfo() throws SQLException {

		String query = "UPDATE customers SET contactLastName = 'asaad' WHERE customerNumber = 999;";

		stmt = con.createStatement();

		int rowInserted = stmt.executeUpdate(query);

		System.out.println(rowInserted);

	}

	@Test(priority = 3, enabled = true)
	public void ReadTheUpdatedData() throws SQLException {
		String query = "SELECT * FROM customers WHERE customerNumber = 999";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);

		while (rs.next()) {
			int customerNumberInDataBase = rs.getInt("customerNumber");
			String customerFirstName = rs.getString("contactFirstName").toString().trim();
			String customerLastName = rs.getString("contactLastName");

			String email = customerFirstName + customerLastName + "@gmail.com";

			String Password = "123 @Pasword";

			System.out.println("Customer Number: " + customerNumberInDataBase);
			System.out.println("First Name: " + customerFirstName);
			System.out.println("Last Name: " + customerLastName);
			System.out.println(email);
			System.out.println(Password);

			driver.findElement(By.id("customer[first_name]")).sendKeys(customerFirstName);
			driver.findElement(By.id("customer[last_name]")).sendKeys(customerLastName);
			driver.findElement(By.id("customer[email]")).sendKeys(email);
			driver.findElement(By.id("customer[password]")).sendKeys(Password);
		}
	}

	@Test(priority = 4, enabled = true)
	public void DeleteCustomer() throws SQLException {
		String query = "DELETE FROM customers WHERE customerNumber = 999";

		stmt = con.createStatement();

		int rowInserted = stmt.executeUpdate(query);

		System.out.println(rowInserted);


	}

}

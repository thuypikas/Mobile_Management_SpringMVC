package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Customers")
public class Customers implements Serializable {
	@Id
	private String CustomerID;
	private String FirstName;
	private String LastName;
	private String Address;
	private String phone;
	private String Email;
	private String picture;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	Collection<Carts> cart;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	Collection<Orders> order;
	@OneToOne(mappedBy="customer",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Accounts account;
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Collection<Carts> getCart() {
		return cart;
	}
	public void setCart(Collection<Carts> cart) {
		this.cart = cart;
	}
	public Collection<Orders> getOrder() {
		return order;
	}
	public void setOrder(Collection<Orders> order) {
		this.order = order;
	}
	public Accounts getAccount() {
		return account;
	}
	public void setAccount(Accounts account) {
		this.account = account;
	}

	
}

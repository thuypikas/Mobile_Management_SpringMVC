package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Accounts implements Serializable {
@Id
private String CustomerID;
@OneToOne
@PrimaryKeyJoinColumn
private Customers customer;
private String UserName;
private String PassWord;
public String getCustomerID() {
	return CustomerID;
}
public void setCustomerID(String customerID) {
	CustomerID = customerID;
}
public Customers getCustomer() {
	return customer;
}
public void setCustomer(Customers customer) {
	this.customer = customer;
}
public String getUserName() {
	return UserName;
}
public void setUserName(String userName) {
	UserName = userName;
}
public String getPassWord() {
	return PassWord;
}
public void setPassWord(String passWord) {
	PassWord = passWord;
}


}

package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="Suppliers")
public class Suppliers implements Serializable {
	@Id @GeneratedValue
	private Integer supplierid;
	private String companyname;
	private String contactname;
	private String contacttitle;
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
	private Collection<Products>product;
	public Integer getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getContacttitle() {
		return contacttitle;
	}
	public void setContacttitle(String contacttitle) {
		this.contacttitle = contacttitle;
	}
	
	public Collection<Products> getProduct() {
		return product;
	}
	public void setProduct(Collection<Products> product) {
		this.product = product;
	}
	
	
}

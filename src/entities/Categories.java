package entities;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name="Categories")
public class Categories implements Serializable{
	@Id @GeneratedValue
	private Integer categoryid;
	@Column(name="CategoryName")
	private String categoryname;
	private String description;
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Collection<Products>product;
	private transient Collection<Suppliers> supplier;
	public Integer getCategoryid() {
		return categoryid;
	}
	public Collection<Suppliers> getSupplier() {
		return supplier;
	}
	public void setSupplier(Collection<Suppliers> supplier) {
		this.supplier = supplier;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<Products> getProduct() {
		return product;
	}
	public void setProduct(Collection<Products> product) {
		this.product = product;
	}
	
}

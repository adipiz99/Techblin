package entity;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int code;
	String name;
	String description;
	int price;
	int quantity;
	int visible;
	
	public ProductBean() {
		code = -1;
		name = "";
		description = "";
		price = 0;
		quantity = 0;
		visible= 0;
	}
	
	public ProductBean(String nome, String descrizione, int prezzo, int quantit�) {
		this.code = -1;
		name = nome;
		description = descrizione;
		price = prezzo;
		quantity = quantit�;
		visible=1;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getVisible() {
		return this.visible;
	}
	
	public void setVisible() {
		this.visible= 1;
	}
	
	public void setInvisible() {
		this.visible= 0;
	}
	
	public boolean isEmpty() {
		return code == -1;
	}
	
	@Override 
	public boolean equals(Object other) {
		return (this.getCode() == ((ProductBean)other).getCode());
	}
	
	@Override
	public String toString() {
		return name + " (" + code +") " + price + ", " + quantity + ", " + description;
	}
	
}

package entity;

import java.io.Serializable;


public class SellerProductBean implements Serializable{

	private static final long serialVersionUID = -6931293601447448394L;
			private int prodotto;
			private String venditore;
			
			public SellerProductBean() {
				this.prodotto= -1;
				this.venditore= "";
				
			}
			
			public SellerProductBean(int prodotto, String venditore) {
				this.prodotto= prodotto;
				this.venditore= venditore;
				
			}
			
			public int getProdotto() {
				return this.prodotto;
			}
			
			public String getVenditore() {
				return this.venditore;
			}
			
			public void setProdotto(int newProdotto) {
				this.prodotto= newProdotto;
			}
			
			public void setVenditore(String newVenditore) {
				this.venditore= newVenditore;
			}
			
	}
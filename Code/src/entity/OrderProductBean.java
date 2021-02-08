package entity;

import java.io.Serializable;


public class OrderProductBean implements Serializable{

	private static final long serialVersionUID = 5764743014793535168L;
			private int prodotto;
			private int ordine;
			
			public OrderProductBean() {
				this.prodotto= -1;
				this.ordine= -1;
			}
			
			public OrderProductBean(int prodotto, int ordine) {
				this.prodotto= prodotto;
				this.ordine= ordine;
				
			}
			
			public int getProdotto() {
				return this.prodotto;
			}
			
			public int getOrdine() {
				return this.ordine;
			}
			
			public void setProdotto(int newProdotto) {
				this.prodotto= newProdotto;
			}
			
			public void setOrdine(int newOrdine) {
				this.ordine= newOrdine;
			}
}

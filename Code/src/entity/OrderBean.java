package entity;

import java.io.Serializable;
import java.sql.Date;

public class OrderBean implements Serializable{
				
	private static final long serialVersionUID = 679784430384538319L;
	
				private int id; 
				private int totale;
				private Date dataOrdine;
				private String nome;
				private String cognome;
				private String stato;
				private String acquirente;
				private String indirizzo;
				private String cap;
				private String città;
				private String provincia;
				private String tipologiaPagamento;
				private String datiPagamento;
				
				public OrderBean() {
					this.acquirente="";
					this.dataOrdine= null;
					this.id= -1;
					this.stato= "";
					this.totale= 0;
					this.indirizzo= "";
					this.cap= "";
					this.città= "";
					this.provincia= "";
					this.tipologiaPagamento= "";
					this.datiPagamento="";
				}
				
				public OrderBean(int id, int totale, Date dataOrdine, String spedizione, String fatturazione, String stato, String acquirente, String indirizzo, String civico, String cap, String città, String provincia, String tipologiaPagamento, String datiPagamento) {
					this.id= id;
					this.totale= totale;
					this.dataOrdine= dataOrdine;
					this.stato= stato;
					this.acquirente= acquirente;
					this.indirizzo= indirizzo;
					this.cap= cap;
					this.città= città;
					this.provincia= provincia;
					this.tipologiaPagamento= tipologiaPagamento;
					this.datiPagamento=datiPagamento;
				}
				
				public OrderBean(String nome, String cognome, String acquirente, String indirizzo, String cap, String città, String provincia, String tipologiaPagamento, String datiPagamento) {
					this.nome = nome;
					this.cognome = cognome;
					this.totale= 100;
					long millis=System.currentTimeMillis();  
			        java.sql.Date date=new java.sql.Date(millis);
					this.dataOrdine= date;
					this.stato= "Ricevuto";
					this.acquirente= acquirente;
					this.indirizzo= indirizzo;
					this.cap= cap;
					this.città= città;
					this.provincia= provincia;
					this.tipologiaPagamento= tipologiaPagamento;
					this.datiPagamento=datiPagamento;
				}
				
				public int getId() {
					return this.id;
				}
				
				public int getTotale() {
					return this.totale;
				}
				
				public Date getDataOrdine() {
					return this.dataOrdine;
				}
				
				public String getStato() {
					return this.stato;
				}
				
				public String getAcquirente() {
					return this.acquirente;
				}
				
				public String getIndirizzo() {
					return this.indirizzo;
				}
				
				public String getCap() {
					return this.cap;
				}
				
				public String getCittà() {
					return this.città;
				}
				
				public String getProvincia() {
					return this.provincia;
				}
				
				public String getTipologiaPagamento() {
					return this.tipologiaPagamento;
				}
				
				public String getDatiPagamento() {
					return this.datiPagamento;
				}
				
				public void setId(int newId) {
					this.id= newId;
				}
				
				public void setTotale(int newTotale) {
					this.totale= newTotale;
				}
				
				public void setDataOrdine(Date newDataOrdine) {
					this.dataOrdine= newDataOrdine;
				}
				
				public void setStato(String newStato) {
					this.stato= newStato;
				}
				
				public void setAcquirente(String newAcquirente) {
					this.acquirente= newAcquirente;
				}
				
				public void setIndirizzo(String newIndirizzo) {
					this.indirizzo= newIndirizzo;
				}
				
				public void setCap(String newCap) {
					this.cap= newCap;
				}
				
				public void setProvincia(String newProvincia) {
					this.provincia= newProvincia;
				}
				
				public void setCittà(String newCittà) {
					this.città= newCittà;
				}
				
				public void setTipologiaPagamento(String newTipologiaPagamento) {
					this.tipologiaPagamento= newTipologiaPagamento;
				}
				
				public void setDatiPagamento(String newDatiPagamento) {
					this.datiPagamento= newDatiPagamento;
				}
				
				
				public String getNome() {
					return nome;
				}

				public void setNome(String nome) {
					this.nome = nome;
				}

				public String getCognome() {
					return cognome;
				}

				public void setCognome(String cognome) {
					this.cognome = cognome;
				}

				public boolean equals(Object o) {
					if(!o.getClass().equals(this.getClass()))
						return false;
					return this.id == ((OrderBean)o).id;
				}
		
}

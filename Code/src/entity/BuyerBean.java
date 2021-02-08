package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BuyerBean implements Serializable{

	private static final long serialVersionUID = -6991725441420684151L;
		
		private String email;
		private String nome;
		private String cognome;
		private String password;
		private String registrazione;
		
		public BuyerBean() {
			this.email= "";
			this.nome= "";
			this.cognome= "";
			this.password= "";
			this.registrazione= "";
		}
		
		public BuyerBean(String email, String nome, String cognome, String password, String registrazione) {
			this.nome= nome;
			this.cognome= cognome;
			this.email= email;
			this.password= password;
			this.registrazione= registrazione;
		}
		
		public BuyerBean(String email, String nome, String cognome, String password) {
			this.nome= nome;
			this.cognome= cognome;
			this.email= email;
			this.password= password;
			String date= (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString();
			this.registrazione=date;
		}
		
		public String getEmail() {
			return this.email;
		}
		
		public String getNome() {
			return this.nome;
		}
		
		public String getCognome() {
			return this.cognome;
		}
		
		public String getPassword() {
			return this.password;
		}
		
		public String getRegistrazione() { //java.sql.Date date = java.sql.Date.valueOf(“date_string”);
			return this.registrazione;
		}
		
		public void setNome(String newNome) {
			this.nome= newNome;
		}
		
		public void setCognome(String newCognome) {
			this.cognome= newCognome;
		}
		
		public void setEmail(String newEmail) {
			this.email= newEmail;
		}
		
		public void setPassword(String newPassword) {
			this.password= newPassword;
		}
		
		public void setRegistrazione(String newRegistrazione) {
			this.registrazione= newRegistrazione;
		}
		
		public boolean equals(Object o) {
			if(!o.getClass().equals(this.getClass()))
				return false;
			return this.email.equalsIgnoreCase(((BuyerBean)o).email);
		}
		
		public String toString() {
			return email + ", " + nome + ", " + cognome;
		}

}

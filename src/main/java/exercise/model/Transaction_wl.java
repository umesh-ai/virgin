package exercise.model;

import java.time.LocalDate;

public class Transaction_wl {

		private LocalDate transactionDate;
		private String vendor;
		private String transactionType;
		private double amt;
		private String category;

		
		public static TransactionBuilder builder() {
	 		return new TransactionBuilder();
	 	}
		
		public static class TransactionBuilder {
			
			private LocalDate transactionDate;
			private String vendor;
			private String transactionType;
			private double amt;
			private String category;
			
			public TransactionBuilder setTransactionDate(LocalDate transactionDate) {
				this.transactionDate = transactionDate;
				return this;
			}




			public TransactionBuilder setVendor(String vendor) {
				this.vendor = vendor;
				return this;
			}




			public TransactionBuilder setTransactionType(String transactionType) {
				this.transactionType = transactionType;
				return this;
			}




			public TransactionBuilder setAmt(double amt) {
				this.amt = amt;
				return this;
			}




			public TransactionBuilder setCategory(String category) {
				this.category = category;
				return this;
			}




			public Transaction_wl build() {
				return new Transaction_wl(this);
			}
			
		}
		
		
		private Transaction_wl(TransactionBuilder transactionBuilder) {
			this.setAmt(transactionBuilder.amt);
			this.setCategory(transactionBuilder.category);
			this.setTransactionDate(transactionBuilder.transactionDate);
			this.setTransactionType(transactionBuilder.transactionType);
			this.setVendor(transactionBuilder.vendor);
		}
		
		public LocalDate getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(LocalDate transactionDate) {
			this.transactionDate = transactionDate;
		}
		public String getVendor() {
			return vendor;
		}
		public void setVendor(String vendor) {
			this.vendor = vendor;
		}
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		public double getAmt() {
			return amt;
		}
		public void setAmt(double amt) {
			this.amt = amt;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}

		public String getTransactionMonth() {
			return getTransactionDate().getMonth().name();
		}
		
		public String getTransactionYear() {
			return String.valueOf(getTransactionDate().getYear());
		}
		
		@Override
		public String toString() {
			return "Transaction [transactionDate=" + transactionDate + ", vendor=" + vendor + ", transactionType="
					+ transactionType + ", amt=" + amt + ", category=" + category + "]";
		}


}

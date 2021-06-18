package exercise.model;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public class Transaction {

		private LocalDate transactionDate;
		private String vendor;
		private String transactionType;
		private double amt;
		private String category;

		
		
		
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

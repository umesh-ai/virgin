package exercise.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exercise.model.Transaction;

public class TransactionDataPopulator {

	public static Optional<List<Transaction>> CreateTransactionData() {
		
		List<Transaction> transactions = new ArrayList<Transaction>(5);
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
		
		
		try {
			transactions.add(populateTransaction(10.40,"card","Morrisons","Groceries",LocalDate.parse("01/Nov/2020",formatter)));
			transactions.add(populateTransaction(600,"direct debit","CYBG","MyMonthlyDD",LocalDate.parse("28/Oct/2020",formatter)));
			transactions.add(populateTransaction(40,"direct debit","PureGym","MyMonthlyDD",LocalDate.parse("28/Oct/2020",formatter)));
			transactions.add(populateTransaction(5.99,"card","M&S","Groceries",LocalDate.parse("01/Oct/2020",formatter)));
			transactions.add(populateTransaction(10,"internet","McMillan",null,LocalDate.parse("20/Sep/2020",formatter)));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.of(transactions);
		
	}

	private static Transaction populateTransaction(double amt,
			String type, String vendor, String category, LocalDate tDate) throws ParseException {
		
		Transaction tran1 = new Transaction();
		tran1.setAmt(amt);
		tran1.setTransactionType(type);
		tran1.setVendor(vendor);
		tran1.setCategory(category);
		tran1.setTransactionDate(tDate);
		
		return tran1;
	}
	
}

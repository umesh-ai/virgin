package exercise.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exercise.model.Transaction;

public class TransactionEvaluator {

	public static final String COMPUTE_TYPE_MAX= "max";
	public static final String COMPUTE_TYPE_MIN= "min";
	
	private List<Transaction> transactions;
	private Logger logger = LoggerFactory.getLogger(TransactionEvaluator.class);
	
	public List<Transaction> getTransactionByCategorySorted(String category) {
		List<Transaction> transactions = getTransactions();
		
		if(null == transactions) {
			logger.info("Populate the list");
			return Collections.<Transaction>emptyList();
		}
		
		return transactions.stream()
	        .filter(t -> null != t.getCategory() && t.getCategory().equalsIgnoreCase(category))
	        .sorted(Comparator.comparing(Transaction::getTransactionDate).reversed())
	        .collect(Collectors.toList());
		
	}

	public Map<String, Object> getTransactionTotalByCategory() {
		List<Transaction> transactions = getTransactions();
		
		if(null == transactions) {
			logger.info("Populate the list");
			return Collections.emptyMap();
		}
		
		return transactions.stream().filter(t -> t.getCategory() != null).collect(Collectors.groupingBy(Transaction::getCategory))
	    .entrySet().stream()
	    .collect(Collectors.toMap(
	    	x -> {
	    		Set<Transaction> targetSet = new TreeSet<>(Comparator.comparing(Transaction::getCategory));
	    		targetSet.addAll(x.getValue());
	    		return targetSet.stream().map(Transaction::getCategory).collect(Collectors.joining(","));},
	    	x -> {
	        return x.getValue().stream().mapToDouble(Transaction::getAmt).sum();
	    		}
	    	));
		
	}
	
	
	public Map<String, Object> getMonthlySpendByCategory(String category) {
		List<Transaction> transactions = getTransactions();
		
		if(null == transactions) {
			logger.info("Populate the list");
			return Collections.emptyMap();
		}
		
		return transactions.stream()
	        .filter(t -> null != t.getCategory() && t.getCategory().equalsIgnoreCase(category))
	        .collect(Collectors.groupingBy(Transaction::getTransactionMonth))
		    .entrySet().stream()
		    .collect(Collectors.toMap(
		    	x -> {
		    		Set<Transaction> targetSet = new TreeSet<>(Comparator.comparing(Transaction::getTransactionMonth));
		    		targetSet.addAll(x.getValue());
		    		return  targetSet.stream().map(Transaction::getTransactionMonth).collect(Collectors.joining(","));},
		    	x -> {
		        return x.getValue().stream().mapToDouble(Transaction::getAmt).average();
		    		}
		    	));
		
	}
	
	public Map<String, Object> getHighestSpendByCategoryForYear(String category) {
		List<Transaction> transactions = getTransactions();
		
		if(null == transactions) {
			logger.info("Populate the list");
			return Collections.emptyMap();
		}
		
		return computeTransactionData(category, transactions, COMPUTE_TYPE_MAX);
		
	}

	public Map<String, Object> getLowestSpendByCategoryForYear(String category) {
		List<Transaction> transactions = getTransactions();
		
		if(null == transactions) {
			logger.info("Populate the list");
			return Collections.emptyMap();
		}
		
		return computeTransactionData(category, transactions, COMPUTE_TYPE_MIN);
		
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public Map<String, Object> computeTransactionData(String category, List<Transaction> transactions, String computeType) {
		return transactions.stream()
	        .filter(t -> null != t.getCategory() && t.getCategory().equalsIgnoreCase(category))
	        .collect(Collectors.groupingBy(Transaction::getTransactionYear))
		    .entrySet().stream()
		    .collect(Collectors.toMap(
		    	x -> {
		    		Set<Transaction> targetSet = new TreeSet<>(Comparator.comparing(Transaction::getTransactionYear));
		    		targetSet.addAll(x.getValue());
		    		return  targetSet.stream().map(Transaction::getTransactionYear).collect(Collectors.joining(","));},
		    	x -> {
			    		switch(computeType) {
				    		case COMPUTE_TYPE_MAX :
				    			return x.getValue().stream().mapToDouble(Transaction::getAmt).max();
				    		case COMPUTE_TYPE_MIN :
				    			return x.getValue().stream().mapToDouble(Transaction::getAmt).min();
				    		default :
				    		 return 0.0;
				    		 	
			    		}
		    		}
		    	));
	}
}

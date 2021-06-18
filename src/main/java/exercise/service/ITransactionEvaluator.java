package exercise.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import exercise.model.Transaction;

public interface ITransactionEvaluator {

	public static final String COMPUTE_TYPE_MAX= "max";
	public static final String COMPUTE_TYPE_MIN= "min";
	
	
	default public Map<String, Object> computeTransactionData(String category, List<Transaction> transactions, String computeType) {
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

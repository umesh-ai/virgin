package exercise;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import exercise.model.Transaction;
import exercise.service.TransactionEvaluator;
import exercise.util.TransactionDataPopulator;

public class CodingExercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(args.length == 0) {
			System.out.println("Enter Category");
			System.exit(0);
		}
		
		List<Transaction> transactionData = 
				TransactionDataPopulator.CreateTransactionData().orElse(Collections.<Transaction>emptyList());
		
		TransactionEvaluator transactionEvaluator = new TransactionEvaluator();
		transactionEvaluator.setTransactions(transactionData);
		
		String category = args[0];
		
		
		List<Transaction> givenCategorySorted = transactionEvaluator.getTransactionByCategorySorted(category);
		System.out.println(givenCategorySorted);
		
		Map<String,Object> totalPerCategory = transactionEvaluator.getTransactionTotalByCategory();
		System.out.println(totalPerCategory);
		
		
		Map<String,Object> monthlyAvgCategory = transactionEvaluator.getMonthlySpendByCategory(category);
		System.out.println(monthlyAvgCategory);
		
		
		Map<String,Object> highestCategoryYear = transactionEvaluator.getHighestSpendByCategoryForYear(category);
		System.out.println(highestCategoryYear);
		
		Map<String,Object> lowestCategoryYear = transactionEvaluator.getLowestSpendByCategoryForYear(category);
		System.out.println(lowestCategoryYear);
		
		
	}

}

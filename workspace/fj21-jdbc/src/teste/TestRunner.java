package teste;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestScheduleDao.class);
		System.out.println("\nRodando testes Schedule DAO");
		for (Failure failure : result.getFailures()) {
		  System.out.println("\t" + failure.toString());
		}
		int success = result.getRunCount() - result.getFailureCount();
		if (result.wasSuccessful())
			System.out.println("[\033[92mOK\033[0m]: passed on " + success + "/" + result.getRunCount());
		else
			System.out.println("[\033[0;31mFAIL\033[0m]: passed on " + success + "/" + result.getRunCount());

		result = JUnitCore.runClasses(TestProfileStateChart.class);
		System.out.println("\nRodando testes Profile State Chart");
		for (Failure failure : result.getFailures()) {
		  System.out.println("\t" + failure.toString());
		}
		success = result.getRunCount() - result.getFailureCount();
		if (result.wasSuccessful())
			System.out.println("[\033[92mOK\033[0m]: passed on " + success + "/" + result.getRunCount());
		else
			System.out.println("[\033[0;31mFAIL\033[0m]: passed on " + success + "/" + result.getRunCount());
	}
} 
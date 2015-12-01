package teste;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
	private static void rodaTeste(Class c, String name) {

		Result result = JUnitCore.runClasses(c);
		System.out.println("\nRodando testes " + name);
		for (Failure failure : result.getFailures()) {
		  System.out.println("\t" + failure.toString());
		}
		int success = result.getRunCount() - result.getFailureCount();
		if (result.wasSuccessful())
			System.out.println("[\033[92mOK\033[0m]: passed on " + success + "/" + result.getRunCount());
		else
			System.out.println("[\033[0;31mFAIL\033[0m]: passed on " + success + "/" + result.getRunCount());

	}

	public static void main(String[] args) {
		rodaTeste(TestScheduleDao.class, "Schedule DAO");
		rodaTeste(TestProfileStateChart.class, "Profile State Chart");
		rodaTeste(TestRegisterStateChart.class, "Register State Chart");
		rodaTeste(TestPatient.class, "Patient");
		rodaTeste(TestMedicine.class, "Medicine");
		rodaTeste(TestDoctor.class, "Doctor");
		System.out.println("");

	}
} 
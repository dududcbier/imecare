package teste;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(TestScheduleDao.class);
    System.out.println("\nRodando testes Schedule DAO\n");
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }
} 
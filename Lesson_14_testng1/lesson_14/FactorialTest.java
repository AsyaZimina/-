package lesson_14;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {
  @Test
  public void testCalculateFactorial() {
      assertEquals(1, Factorial.calculateFactorial(0));
      assertEquals(1, Factorial.calculateFactorial(1));
      assertEquals(120, Factorial.calculateFactorial(5));
      assertEquals(3628800, Factorial.calculateFactorial(10));
  }

  @Test
  public void testCalculateFactorialNegativeNumber() {
      assertThrows(IllegalArgumentException.class, () -> Factorial.calculateFactorial(-5));
  }
}

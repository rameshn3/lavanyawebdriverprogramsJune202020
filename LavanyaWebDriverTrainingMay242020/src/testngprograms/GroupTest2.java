package testngprograms;

import org.testng.annotations.Test;

public class GroupTest2 {
  @Test(groups= {"smoke"})
  public void smoketest1() {
	  System.out.println("This smoketest1 belongs to GroupTest2 class - smoke group");
  }
  
  @Test(groups= {"smoke"})
  public void smokeTest2() {
	  System.out.println("This smoketest2 belongs to GroupTest2 class - smoke group");
  }
  
  @Test(groups= {"smoke"})
  public void smokeTest3() {
	  System.out.println("This smoketest3 belongs to GroupTest2 class - smoke group");
  }
  
  @Test(groups= {"regression"})
  public void regTest4() {
	  System.out.println("This regtest4 belongs to GroupTest2 class - regression group");
  }
  
  @Test(groups= {"regression"})
  public void regTest5() {
	  System.out.println("This regtest5 belongs to GroupTest2 class - regression group");
  }
  
  @Test(groups= {"regression"})
  public void regTest6() {
	  System.out.println("This regtest6 belongs to GroupTest2 class - regression group");
  }
  
  @Test
  public void regTest7() {
	  System.out.println("This regtest7 belongs to GroupTest2 class - regression group");
  }
  
}

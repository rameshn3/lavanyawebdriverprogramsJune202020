package testngprograms;

import org.testng.annotations.Test;

public class GroupTest {
  @Test(groups= {"smoke"})
  public void smoketest1() {
	  System.out.println("This test1 belongs to GroupTest class - smoke group");
  }
  
  @Test(groups= {"smoke"})
  public void smokeTest2() {
	  System.out.println("This test2 belongs to GroupTest class - smoke group");
  }
  
  @Test(groups= {"smoke"})
  public void smokeTest3() {
	  System.out.println("This test3 belongs to GroupTest class - smoke group");
  }
  
  @Test(groups= {"regression"})
  public void regTest4() {
	  System.out.println("This test4 belongs to GroupTest class - regression group");
  }
  
  @Test(groups= {"regression"})
  public void regTest5() {
	  System.out.println("This test5 belongs to GroupTest class - regression group");
  }
  
  @Test(groups= {"regression"})
  public void regTest6() {
	  System.out.println("This test6 belongs to GroupTest class - regression group");
  }
  
  @Test
  public void regTest7() {
	  System.out.println("This test7 belongs to GroupTest class - regression group");
  }
  
}

package thetestingacademy.sep05.testngexample;

import org.testng.annotations.*;

public class AllAnnotataion {
    @BeforeSuite
    public void demo1(){
        System.out.println("before suite");
    }
    @BeforeTest
    public void demo2(){
        System.out.println("before test");
    }
    @BeforeClass
    public void demo3(){
        System.out.println("before class");
    }
    @BeforeMethod
    public void demo4(){
        System.out.println("before method");
    }
    @Test
    public void demo5_1(){
        System.out.println("login sql");
    }
    @Test
    public void demo5_2(){
        System.out.println("login sql again");
    }
    @AfterMethod
    public void demo6(){
        System.out.println("after method");
    }
    @AfterClass
    public  void demo7(){
        System.out.println("after class");
    }
    @AfterTest
    public void demo8(){
        System.out.println("after test");
    }
    @AfterSuite
    public void demo9(){
        System.out.println("close the sql");
    }

}

package thetestingacademy.sep05.testngexample;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNG003 {
    @Test
    public void test1(){
        System.out.println("Test1");
        String name="pramod";
        //Soft Assertion
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals("pramod",name,"passed");
        softAssert.assertAll();


    }
    @Test
    public void test2(){
        System.out.println("Test2");
        String name="pramod";
        //HardAssertion
        Assert.assertEquals("pramodd",name,"failed");
        System.out.println("End");
    }
}

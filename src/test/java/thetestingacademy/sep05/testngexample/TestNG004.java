package thetestingacademy.sep05.testngexample;

import org.testng.annotations.Test;

public class TestNG004 {
    @Test(groups = {"smoke","qa"})
    public void test1(){
        System.out.println("Test1-Smoke");
    }
    @Test(groups = {"smoke","preprod"})
    public void test2(){
        System.out.println("Test2-Smoke");
    }
    @Test(groups = {"regression","qa"})
    public void test3(){
        System.out.println("Test3-Regression");
    }
}

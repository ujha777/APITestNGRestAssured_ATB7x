package thetestingacademy.sep05.TestNG;

import org.testng.annotations.Test;

public class Testng001 {
    @Test
    public void serverStartedOk() {
        System.out.println("I will run First");
    }

    @Test(dependsOnMethods = {"serverStartedOk"})
    public void method1() {
        System.out.println("Run Method 1");
    }
}

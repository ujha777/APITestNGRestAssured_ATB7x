package thetestingacademy.sep05.TestNG;

import org.testng.annotations.Test;

public class Testng002 {
    @Test(groups = { "init" })
    public void serverStartedOk() {
        System.out.println("serverStartedOk");
    }

    @Test(groups = { "init" })
    public void initEnvironment() {
        System.out.println("initEnvironment");
    }

    @Test(dependsOnGroups = { "init.*" })
    public void method1() {
        System.out.println("Now I will run");
    }
}

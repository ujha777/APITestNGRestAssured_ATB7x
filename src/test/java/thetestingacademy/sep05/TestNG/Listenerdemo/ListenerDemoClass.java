package thetestingacademy.sep05.TestNG.Listenerdemo;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//@Listeners(CustomListeners.class)
@Listeners(CustomListeners2.class)
public class ListenerDemoClass {

    @Test(groups = "school")
    public void test1(){
        System.out.println("test1");
        Assert.assertTrue(false);
    }
    @Test(groups = "party")
    public void test2(){
        System.out.println("test2");
        Assert.assertTrue(true);
    }
}

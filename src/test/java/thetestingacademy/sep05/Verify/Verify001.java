package thetestingacademy.sep05.Verify;

import org.testng.Assert;
import org.testng.AssertJUnit;

public class Verify001 {
    public static void main(String[] args) {
        String responseName="Pramod";
        Assert.assertEquals("Pramod",responseName);
    }
}

package thetestingacademy.sep05.TestNG.Listenerdemo;

import org.testng.IExecutionListener;

public class CustomListeners implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        long endTime= System.currentTimeMillis();
        System.out.println("****   *** Finished execution at- "+ endTime +"****   *** ");

    }

    @Override
    public void onExecutionFinish() {
        long startTime= System.currentTimeMillis();
        System.out.println(" ****   *** Started execution at - "+ startTime + "****   *** ");

    }
}

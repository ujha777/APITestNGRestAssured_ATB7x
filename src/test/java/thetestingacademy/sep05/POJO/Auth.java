package thetestingacademy.sep05.POJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.testng.annotations.Test;

@Test
public class Auth {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
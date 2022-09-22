import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }

    }

    @Test
    public void LoginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("nik@gmail.com","123589$Nik");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isLoggedWind());


    }
}

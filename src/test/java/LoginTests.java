import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logout();
            }

    @Test
    public void LoginSuccess(){
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm("nik@gmail.com","123589$Nik");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
      //  Assert.assertTrue(app.getHelperUser().isLoggedWind()); hw

    }
    @Test
    public void LoginSuccessModels(){

        User user = new User().withEmail("nik@gmail.com").withPassword("123589$Nik");
        app.getHelperUser().openLoginFormFooter();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }

    @Test
    public void negativeWrongEmail(){
        User user = new User().withEmail("nikgmail.com").withPassword("123589$Nik");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert errorMessage
        //Assert button not active
        //HW
//        if(app.getHelperUser().isElementPresent(By.cssSelector("div.dialog-container"))){
//            Assert.assertEquals(app.getHelperUser().getMessage(),"Wrong email or password");
//        }else {
//            Assert.assertTrue(app.getHelperUser().isElementPresent(By.cssSelector("[disabled]")));
//        }

        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void negativeWrongPassword(){
        User user = new User().withEmail("nik@gmail.com").withPassword("Wwow12345$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //text message "Authorization error" // "Wrong email or password"
        Assert.assertEquals(app.getHelperUser().getMessage(),"Wrong email or password"); ////HW
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Authorization error");
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickButton();
    }
}

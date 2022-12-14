import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {

        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("The logout was needed ");
        }
    }

    @Test(dataProvider = "datalogin", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password) {
        logger.info("Login scenario success was used data email:" + email + " & password: " + password);

        app.getHelperUser().openLoginFormHeader();
        // app.getHelperUser().fillLoginForm("noa@gmail.com","Nnoa12345$");
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("In assert checked message 'Logged in success' in dialog  ");
        //  Assert.assertTrue(app.getHelperUser().isLoggedWind()); hw

    }

    @Test(dataProvider = "dataModelUser", dataProviderClass = DataProviderUser.class)
    public void LoginSuccessModelsDP(User user) {

        logger.info("Login scenario success was used data" + user.toString());
        app.getHelperUser().openLoginFormFooter();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("In assert checked message 'Logged in success' in dialog  ");

    }

    @Test(groups = {"smoke"})
    public void LoginSuccessModels() {

        User user = new User().withEmail("nik@gmail.com").withPassword("123589$Nik");
        logger.info("Login scenario success was used data" + user.toString());
        app.getHelperUser().openLoginFormFooter();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("In assert checked message 'Logged in success' in dialog  ");

    }

    @Test
    public void negativeWrongEmail() {
        User user = new User().withEmail("nikgmail.com").withPassword("123589$Nik");
        logger.info("Login negative scenario with wrong email was used data" + user.toString());
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
         app.getHelperUser().submitWithoutWait();
        //Assert errorMessage
        //Assert button not active
        //HW
//        if(app.getHelperUser().isElementPresent(By.cssSelector("div.dialog-container"))){
//            Assert.assertEquals(app.getHelperUser().getMessage(),"Wrong email or password");
//        }else {
//            Assert.assertTrue(app.getHelperUser().isElementPresent(By.cssSelector("[disabled]")));
//        }

        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("In assert checked error message 'It'snot look like email' under name field ");
    }

    @Test
    public void negativeWrongPassword() {
        User user = new User().withEmail("nik@gmail.com").withPassword("Wwow12345$");
        logger.info("Login negative scenario with wrong password was used data" + user.toString());
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitWithoutWait();
        //text message "Authorization error" // "Wrong email or password"
        Assert.assertEquals(app.getHelperUser().getMessage(), "Wrong email or password"); ////HW
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Authorization error");
        logger.info("In assert checked message 'Authorization error' & 'Wrong email or password' in dialog  ");
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}

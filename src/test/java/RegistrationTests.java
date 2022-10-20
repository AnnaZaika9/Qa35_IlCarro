import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logout();
        logger.info("The logout was needed ");
    }

    @Test(dataProvider =  "regDataValid",dataProviderClass = DataProviderUser.class)
    public void registrationSuccessDP(User user){


        logger.info("Registration scenario success was used data"+user.toString());
        app.getHelperUser().openRegistrationFormHeader();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");
        logger.info("In assert checked message 'Registered' in dialog  ");
    }

    @Test
    public void registrationSuccess(){
        System.out.println(System.currentTimeMillis());
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withName("Nikita").withLastname("Snow").withEmail("nik"+i+"@ukr.net").withPassword("Ww12345$");

        logger.info("Registration scenario success was used data"+user.toString());
        app.getHelperUser().openRegistrationFormHeader();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");
        logger.info("In assert checked message 'Registered' in dialog  ");
    }
    @Test
    public void registrationWrongPassword(){
        User user = new User().withName("Mikita").withLastname("Snow").withEmail("nik@ukr.net").withPassword("Mikita");
        logger.info("Registration negative scenario with wrong password was used data"+user.toString());
        app.getHelperUser().openRegistrationFormHeader();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
     //   app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("The Y'alla button was not active");
    }

    @AfterMethod
    public void postCondition(){

        app.getHelperUser().clickOkButton();
    }

}

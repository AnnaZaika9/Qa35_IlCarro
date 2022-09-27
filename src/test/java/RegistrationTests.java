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
    }

    @Test
    public void registrationSuccess(){
        System.out.println(System.currentTimeMillis());
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withName("Nikita").withLastname("Snow").withEmail("nik"+i+"@ukr.net").withPassword("Ww12345$");

        app.getHelperUser().openRegistrationFormHeader();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");
    }
    @Test
    public void registrationWrongPassword(){
        User user = new User().withName("Mikita").withLastname("Snow").withEmail("nik@ukr.net").withPassword("Mikita");
        app.getHelperUser().openRegistrationFormHeader();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickButton();
    }

}

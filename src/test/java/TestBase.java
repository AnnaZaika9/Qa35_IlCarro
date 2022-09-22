import manager.ApplicationManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    static ApplicationManager app = new ApplicationManager();


    @BeforeSuite
    public void setUp(){
       app.init();

    }


    @AfterTest
    public void tearDown(){
        app.stop();

    }

}

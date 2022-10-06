import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);
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

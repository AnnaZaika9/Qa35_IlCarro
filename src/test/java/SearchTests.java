import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getSearch().searchCurrentMonth2("Tel Aviv","10/25/2022","10/30/2022");
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
        logger.info("In the assertion, it is checked whether there were cars for the current month");
    }
    @Test
    public void searchNextMonthSuccess(){
        app.getSearch().searchNextMonth("Tel Aviv","11/25/2022","11/30/2022");
        app.getSearch().pause(500);
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
        logger.info("In the assertion, it is checked whether there were cars for the next month");
    }
    @AfterMethod
    public void posCondition(){

        app.helperCar().returnToSearchPage();

    }
}

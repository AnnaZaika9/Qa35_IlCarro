import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{



    @Test
    public void searchCurrentMonthSuccess(){
        app.getSearch().searchCurrentMonth2("Tel Aviv","10/28/2022","10/30/2022");
        app.getSearch().pause(500);
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

    @Test
    public void searchAnyPeriod() {
        app.getSearch().selectAnyPeriod("Tel Aviv", "2/20/2023", "6/10/2023");
        app.getSearch().pause(500);
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
        // Assert.assertTrue(app.getSearch().isDataCorrect("2/20/2023","6/10/2023"));
    }
        @Test
        public void searchInPast(){
            app.getSearch().typePeriodInPast("Tel Aviv","10/5/2022","10/10/2022");
            app.getSearch().submit();

            Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
            Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed()); //You can't pick date before today
        }



    @AfterMethod
    public void posCondition(){

        app.helperCar().returnToSearchPage();
        //app.getSearch().typePeriodInPast("","","");
       app.getSearch().clearFields();

    }
}

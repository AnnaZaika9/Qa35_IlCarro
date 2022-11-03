
import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
         if (!app.getHelperUser().isLogged()){
             User user = new User().withEmail("nik@gmail.com").withPassword("123589$Nik");
             app.getHelperUser().login(user);
             logger.info("The login was needed with user : " +user.toString());
         }

    }

    @Test(groups = {"smoke","sanity"})
    public void addCarSuccess(){
        Random random = new Random();
        int i =  random.nextInt(1000)+100;

        Car car = Car.builder()
                .location("Haifa,Israel")
                .make("BMW")
                .model("M5")
                .year("2020")
                .engine("2.5")
                .fuel("Petrol")
                .gear("AT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .clasS("C")
                .fuelConsumption("6.5")
                .carRegistrationNumber("12-897-"+i)
                .price("65")
                .distanceIncluded("800")
                .features("Type of features")
                .about("very nice car")
                .build();
        logger.info("The test used car model : " +car.toString());

        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("C:\\Users\\user\\Desktop\\anna\\TelRun\\Lessons\\GitHub\\Qa35_IlCarro\\src\\test\\resources\\car.jpg");
        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
        // logger.info("User adds car with data: " + car.toString());
        //  logger.info("Assert passed");
        logger.info("In assert checked message 'Car added' in dialog  ");


    }
    @Test(dataProvider = "carValidData",dataProviderClass = DataProviderCar.class)
    public void addCarSuccessDP(Car car){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        car.setCarRegistrationNumber("111-899-" + i); //// временная заглушка


        logger.info("The test used car model : " +car.toString());

        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("C:\\Users\\user\\Desktop\\anna\\TelRun\\Lessons\\GitHub\\Qa35_IlCarro\\src\\test\\resources\\car.jpg");
       app.helperCar().pause(2000);
        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
        logger.info("In assert checked message 'Car added' in dialog  ");


    }


    @AfterMethod(alwaysRun = true)
    public void posCondition(){
        app.helperCar().returnToHomePage();
      //  logger.info("User returned to home page ");
    }


}

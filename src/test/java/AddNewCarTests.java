import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
         if (!app.getHelperUser().isLogged()){
             app.getHelperUser().login(new User().withEmail("nik@gmail.com").withPassword("123589$Nik"));
         }

    }
    @Test
    public void addCarSuccess(){
        Random random = new Random();
       int i =  random.nextInt(1000)+100;

        logger.info("Test start with name: addCarSuccess");
        logger.info("User login with data: email nik@gmail.com & password 123589$Nik ");

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
                .carRegistrationNumber("11-000-"+i)
                .price("65")
                .distanceIncluded("800")
                .features("Type of features")
                .about("very nice car")
                .build();

        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("C:\\Users\\user\\Desktop\\anna\\TelRun\\Lessons\\GitHub\\Qa35_IlCarro\\src\\test\\resources\\car.jpg");
        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");

        logger.info("User adds car with data: " + car.toString());
        logger.info("Assert passed");


    }

    @AfterMethod
    public void posCondition(){
        app.helperCar().returnToHomePage();
        logger.info("User returned to home page ");
    }

}
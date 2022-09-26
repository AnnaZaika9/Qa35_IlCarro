package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {


    public HelperUser(WebDriver wd) {

        super(wd);
    }

//    public boolean isLoggedWind() {
//        String text = wd.findElement(By.xpath("//h2[text()='Logged in success']")).getText();
//        return text.contains("Logged in success");
//
//    }

//    public boolean isLogged() {
//        List<WebElement> list = wd.findElements(By.cssSelector(".header :nth-child(5)"));
//        return list.size() > 0;
//   }
public boolean isLogged() {

        return  isElementPresent(By.xpath("//a[text()=' Logout ']"));
}

    public void logout() {
        wd.findElement(By.xpath("//a[text()=' Logout ']")).click();
        //".header :nth-child(5)")

    }

    public void openLoginFormHeader() {
        WebElement loginTab = wd.findElement(By.cssSelector("a[href ^='/login']"));
        //".header :nth-child(6)"
        //"//a[text()=' Log in ']"
        loginTab.click();
    }

    public void openLoginFormFooter() {
        wd.findElement(By.xpath("//a[text()='Log in']")).click();
    }

    public void fillLoginForm(String email, String password) {

        type(By.id("email"), email);
        type(By.id("password"), password);
    }
    public void fillLoginForm(User user) {

        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }


    public String getMessage() {
        //pause(2000);
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dialog-container"))));

        return wd.findElement(By.cssSelector("h2.message")).getText();
    }

    public void clickButton() {
        if(isElementPresent(By.cssSelector("div.dialog-container"))){
            click(By.xpath("//button[text()='Ok']"));
        }
    }
}

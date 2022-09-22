package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase {


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLoggedWind(){
       String text =  wd.findElement(By.xpath("//h2[text()='Logged in success']")).getText();
         return text.contains("Logged in success");

}
    public boolean isLogged(){
        List<WebElement> list = wd.findElements(By.cssSelector(".header :nth-child(5)"));
        return list.size() > 0;
    }
    public void logout(){
        wd.findElement(By.cssSelector(".header :nth-child(5)")).click();

    }

    public void openLoginForm(){
        WebElement loginTab = wd.findElement(By.cssSelector(".header :nth-child(6)"));
        loginTab.click();
    }
    public void fillLoginForm(String email,String password){

        type(By.cssSelector("#email"), email);
       type(By.cssSelector("#password"),password);
    }

    public void submit(){
        wd.findElement(By.xpath("//button[text()='Y’alla!']")).click();
        wd.findElement(By.xpath("//button[text()='Ok']")).click();



    }


}

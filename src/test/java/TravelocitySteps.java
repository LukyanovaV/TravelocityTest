import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TravelocitySteps {

    WebDriver driver;

    By goingTo = By.id("hotel-destination-hp-hotel");
    By dstnListBox = By.id("typeaheadDataPlain");
    By dstList = By.xpath("//*[@id=\"typeaheadDataPlain\"]/div/li");
    By checkIn = By.id("hotel-checkin-hp-hotel");
    By checkOut = By.id("hotel-checkout-hp-hotel");
    By btnSubmit = By.xpath("//*[@id=\"gcw-hotel-form-hp-hotel\"]/div[8]/label/button");
    By hotelsList = By.cssSelector("a.flex-link");
    By reserveHotelBtn = By.cssSelector("button[id^='mock-book-button']");
//#hotel-destination-hp-hotel

    TravelocitySteps(WebDriver driver){
        this.driver = driver;
    }

    public void openStartPage(){
        driver.get("https://www.travelocity.com/");
    }

    public void inputDataGoingTo(String dest, String region){
        driver.findElement(goingTo).click();
        driver.findElement(goingTo).sendKeys(dest);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(dstnListBox));
        List<WebElement> dstnList = driver.findElements(dstList);
        dstnList.stream().filter((s) -> s.getText().contains(region)).findFirst().get().click();
    }

    public void inputCheckIn(String mmDDYyyy){
        driver.findElement(checkIn).sendKeys(mmDDYyyy);

    }

    public void inputCheckOut(String mmDDYyyy){
        driver.findElement(checkOut).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(checkOut).click();
        driver.findElement(checkOut).clear();

        for (int a = 0;a<50;a++) {
            driver.findElement(checkOut).sendKeys(Keys.BACK_SPACE);

        }
        driver.findElement(checkOut).sendKeys(mmDDYyyy);
    }

    public void submitData(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();

    }

    public void chooseHotel(String hotelName){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#hotelResultTitle > h1")));
        List<WebElement> hotelList = driver.findElements(hotelsList);

        hotelList.stream().filter((s)->s.getAttribute("target").contains(hotelName)).findFirst().get().click();
    }

    public void reservedRecomended(){
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window((String) tabs.get(1));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(reserveHotelBtn)).click();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"rooms-and-rates\"]/div/article/table/tbody[1]/tr[1]/td[3]/div/form/div[1]/button"));
        element.sendKeys(Keys.ENTER);
    }




}

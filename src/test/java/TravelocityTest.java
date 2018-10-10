import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class TravelocityTest extends BaseTest{

    public String destination = "Japantown";
    public String region = "San Francisco";
    public String CheckInData = "10/13/2018";
    public String CheckoutData = "10/16/2018";
    public String hotelNum = "5901";

    @Test
    public void chooseRecomendedHotelTest(){
        steps.openStartPage();
        steps.inputDataGoingTo(destination, region);
        steps.inputCheckIn(CheckInData);
        steps.inputCheckOut(CheckoutData);
        steps.submitData();
        steps.chooseHotel(hotelNum);
        steps.reservedRecomended();
        Assert.assertTrue(!(driver.findElements(By.id("secondary-content")).isEmpty()));

    }
}

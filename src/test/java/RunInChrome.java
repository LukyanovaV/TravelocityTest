import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( TravelocityTest.class)
public class RunInChrome {

    @BeforeClass
    public static void setBrowser() {
        System.setProperty("browser", "Chrome");
    }
}


package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.LandingPage;

public class BaseTest {

    protected WebDriver driver;
    public LandingPage landingpage;

    
    public void setup() throws IOException {
        driver = initializeDriver();
    }

    private WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
        prop.load(fis);
        

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        //prop.getProperty("browser");

        switch (browserName.toLowerCase()) {
            case "chrome":         	
            	ChromeOptions options = new ChromeOptions();
            	if(browserName.contains("headless")) {
            		options.addArguments("headless");
            	}
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440,900));
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jSonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jSonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public void getScreenshot(String testCaseName) throws IOException {
//    	File file=name.getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(file, new File("Logo.png"));
    	if (driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
            try {
                FileHandler.copy(source, new File(destination));
                System.out.println("Screenshot saved at: " + destination);
            } catch (IOException e) {
                System.out.println("Screenshot capture failed: " + e.getMessage());
            }
        } else {
            System.out.println("Driver is null, cannot take a screenshot.");
        }
    }
        
        
    

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
    @BeforeMethod(alwaysRun=true)
    protected LandingPage LaunchApplication() throws IOException {
    	driver = initializeDriver();
    	if(driver != null) {
        landingpage = new LandingPage(driver);
        landingpage.goTo();
        return landingpage;
    }
    	return null;
    }
    public WebDriver getDriver() {
        return driver;
    }
}



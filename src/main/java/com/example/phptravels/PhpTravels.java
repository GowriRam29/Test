package com.example.phptravels;
import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class PhpTravels {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver","/Users/gowri/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.MINUTES);
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void testPhpTravels() throws InterruptedException {
        driver.get("http://www.phptravels.net/");
        String search_city = "Singapore";
        WebDriverWait wait = new WebDriverWait(driver, 150);
        WebElement searchField =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"s2id_autogen3\"]/a/span[1]")));
        searchField.click();
        WebElement searchEntry = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
        searchEntry.sendKeys(search_city);
        searchEntry.sendKeys(Keys.RETURN);
        //Wait
        By waitFor = By.cssSelector("#select2-drop > ul > li:nth-child(1) > ul > li > div");
        WebElement hotelResult = wait.until(ExpectedConditions.visibilityOfElementLocated(waitFor));
        hotelResult.click();


        //Checkin Date
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calobj = Calendar.getInstance();
        System.out.println(df.format(calobj.getTime()));
        calobj.add(Calendar.DATE, 10);
        String checkInDay = df.format(calobj.getTime());
        calobj.add(Calendar.DATE, 5);
        String checkOutDay = df.format(calobj.getTime());
        System.out.println(checkInDay);
        System.out.println(checkOutDay);
        WebElement checkinDate = driver.findElement(By.xpath("//*[@id=\"dpd1\"]/div/input"));
        checkinDate.click();
        checkinDate.sendKeys(checkInDay);

        WebElement checkoutDate = driver.findElement(By.xpath("//*[@id=\"dpd2\"]/div/input"));
        checkoutDate.click();
        checkoutDate.sendKeys(checkOutDay);

        WebElement travellerInput = driver.findElement(By.xpath("//*[@id=\"travellersInput\"]"));
        travellerInput.click();
        WebElement childNo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"childPlusBtn\"]")));
        childNo.click();
        WebElement cookiepopUp =  driver.findElement(By.xpath("//*[@id=\"cookyGotItBtn\"]"));
        cookiepopUp.click();
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"hotels\"]/form/div[5]/button"));
        searchButton.click();
        String expectedRoomType =  "JUNIOR SUITES";

        /*WebElement roomType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr[1]/td/div[2]/div[1]/div[1]/h4/a/b")));
        //WebElement roomType = driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr[2]/td/div[2]/div[1]/div[1]/h4/a/b"));
        WebElement roomCSS = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ROOMS > div > table > tbody > tr:nth-child(1) > td > div.col-md-10.col-xs-8.g0-left > div.col-md-4.go-right > div:nth-child(1) > h4 > a > b")));
        System.out.println("ROOM"+roomType.getText());*/
       WebElement roomElement = driver.findElement(By.cssSelector("#ROOMS > div > table > tbody > tr:nth-child(7) > td > div.col-md-10.col-xs-8.g0-left > div.col-md-4.go-right > div:nth-child(1) > h4 > a > b"));
       String roomType = roomElement.getAttribute("innerText");


        System.out.println("ROOM"+roomType);
        String roomvalue = "JUNIOR SUITES";
        Assert.assertEquals(roomType,roomvalue);

       WebElement priceElement= driver.findElement(By.cssSelector("#ROOMS > div > table > tbody > tr:nth-child(1) > td > div.col-md-10.col-xs-8.g0-left > div.col-md-8.book_area.go-left > div > div.col-md-4.col-xs-6.go-right > h2 > strong"));
        String price = priceElement.getAttribute("innerText");
        System.out.println("PRICE"+price);
        String priceExpected = "1250 $";
        Assert.assertEquals(price,priceExpected);
    }

}

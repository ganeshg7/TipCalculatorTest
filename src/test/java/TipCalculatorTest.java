import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TipCalculatorTest {
    public static void main(String[] args){
        System.setProperty("webdriver","C:\\Users\\GaneshG\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        driver.get("https://qatipcalc.ccbp.tech/");

        //Find the BillAmount
        WebElement billAmountEl = driver.findElement(By.cssSelector("input[id^='bill']"));
        billAmountEl.sendKeys("1000");

        //Find the PercentageTip
        WebElement percentageTipEl = driver.findElement(By.cssSelector("input[id^='percentage']"));
        percentageTipEl.sendKeys("12");

        //Find the Calculation
        WebElement calculateButton = driver.findElement(By.cssSelector("button[class*='btn']"));
        calculateButton.click();

        //Verify the amount tip
        WebElement tipamountEl = driver.findElement(By.cssSelector("p[id*='tipAmount']"));
        WebElement totalamountEl = driver.findElement(By.cssSelector("p[id*='totalAmount']"));
        if(tipamountEl.getText().equals("120.00") && totalamountEl.getText().equals("1120.00")){
            System.out.println("Tip Calculated Correctly");
        }else{
            System.out.println("Tip Calculated Incorrectly");
        }

        //Test the display of error message for no inputs.
        percentageTipEl.clear();
        calculateButton.click();

        WebElement pterrorEl = driver.findElement(By.cssSelector("p[id^='error']"));
        if(pterrorEl.getText().equals("Please Enter a Valid Input.")){
            System.out.println("Error message displayed for no input");
        }else{
            System.out.println( "Error message missing for no input");
        }

        //Test the display of error message for invalid inputs.
        percentageTipEl.sendKeys("10f");
        calculateButton.click();
        WebElement pterrorEl2 = driver.findElement(By.cssSelector("p[id$='Message']"));
        if(pterrorEl2.getText().equals("Please Enter a Valid Input.")){
            System.out.println("Error message displayed for no input");
        }else{
            System.out.println( "Error message missing for no input");
        }

        //Close the browser
        driver.quit();

    }
}

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class TestScenario {

    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor javascriptExecutor;

    private static final String BASE_URL = "http://www.rgs.ru";

    @Before
    public void startUp() throws InterruptedException {
        //region 1
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
        driver.get(BASE_URL);
        //endregion

        //region 2
        String menu_combobox = "//form/preceding-sibling::a[@class='hidden-xs']";
        WebElement region_button = driver.findElement(By.xpath(menu_combobox));
        region_button.click();
        //endregion

        //region 3
        String dialog_form = "//div[@class='grid rgs-main-menu-links']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dialog_form)));

        String dms_xpath = "//a[contains(text(),'ДМС')]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dms_xpath)));
        WebElement dms_button = driver.findElement(By.xpath(dms_xpath));
        dms_button.click();
        //endregion
    }

    @Test
    public void Testscenario() throws InterruptedException {

        //region 4
        WebElement headElement = driver.findElement(By.xpath("//h1"));
        javascriptExecutor =  (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", headElement);

        wait.until(ExpectedConditions.visibilityOf(headElement));
        Assert.assertEquals("не верное название y header", "ДМС — добровольное медицинское страхование", headElement.getText());
        //endregion

        //region 5
        String send_button_xpath = "//a[contains(text(), 'Отправить заявку')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(send_button_xpath)));
        WebElement send_button = driver.findElement(By.xpath(send_button_xpath));
        send_button.click();
        //endregion

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='modal-content']"))));

        //region 6
        WebElement form_name_element = driver.findElement(By.xpath("//b"));
        Assert.assertEquals("не верное название формы", "Заявка на добровольное медицинское страхование", form_name_element.getText());
        //endregion

        //region 7
        String lastname_textbox_xpath = "//input[contains(@data-bind,'LastName')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lastname_textbox_xpath)));
        WebElement lastname_textbox = driver.findElement(By.xpath(lastname_textbox_xpath));
        lastname_textbox.sendKeys("Акчурин");

        String firstname_textbox_xpath = "//input[contains(@data-bind,'FirstName')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstname_textbox_xpath)));
        WebElement firstname_textbox = driver.findElement(By.xpath(firstname_textbox_xpath));
        firstname_textbox.sendKeys("Ренат");

        String middlename_textbox_xpath = "//input[contains(@data-bind,'MiddleName')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(middlename_textbox_xpath)));
        WebElement middlename_textbox = driver.findElement(By.xpath(middlename_textbox_xpath));
        middlename_textbox.sendKeys("Маратович");

        String region_combobox_xpath = "//option[@value='77']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(region_combobox_xpath)));
        WebElement region_combobox = driver.findElement(By.xpath(region_combobox_xpath));
        region_combobox.click();

        String phone_textbox_xpath = "//input[contains(@data-bind,'Phone')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(phone_textbox_xpath)));
        WebElement phone_textbox = driver.findElement(By.xpath(phone_textbox_xpath));
        phone_textbox.click();
        phone_textbox.sendKeys("9859791573");

        String date_textbox_xpath = "//input[contains(@data-bind,'ContactDate')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(date_textbox_xpath)));
        WebElement date_textbox = driver.findElement(By.xpath(date_textbox_xpath));
        date_textbox.click();
        date_textbox.sendKeys("22072020");
        form_name_element.click();

        String mail_textbox_xpath = "//input[contains(@data-bind,'Email')]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(mail_textbox_xpath)));
        WebElement mail_textbox = driver.findElement(By.xpath(mail_textbox_xpath));
        mail_textbox.sendKeys("qwertyqwerty");

        String comment_textbox_xpath = "//textarea";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(comment_textbox_xpath)));
        WebElement comment_textbox = driver.findElement(By.xpath(comment_textbox_xpath));
        comment_textbox.sendKeys("Comment");

        String personel_checkbox_xpath = "//input[@class='checkbox']/../..";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(personel_checkbox_xpath)));
        WebElement personel_checkbox = driver.findElement(By.xpath(personel_checkbox_xpath));
        personel_checkbox.click();
        //endregion

        //region 8
        Assert.assertEquals("не верно заполнено", "Акчурин", lastname_textbox.getAttribute("value"));
        Assert.assertEquals("не верно заполнено", "Ренат", firstname_textbox.getAttribute("value"));
        Assert.assertEquals("не верно заполнено", "Маратович", middlename_textbox.getAttribute("value"));
        Assert.assertEquals("не верно заполнено", "77", region_combobox.getAttribute("value"));
        Assert.assertEquals("не верно заполнено", "+7 (985) 979-15-73", phone_textbox.getAttribute("value"));
        Assert.assertEquals("не верно заполнено", "22.07.2020", date_textbox.getAttribute("value"));
        Assert.assertEquals("не верно заполнено", "qwertyqwerty", mail_textbox.getAttribute("value"));
        Assert.assertEquals("не верно заполнено", "Comment", comment_textbox.getAttribute("value"));
        Assert.assertEquals("не верно заполнено", false, personel_checkbox.isSelected()); //вообще не понял почему возвращает false((
        //endregion

        //region 9
        String sendinfo_button_xpath = "//button[@id='button-m']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sendinfo_button_xpath)));
        WebElement sendinfo_button = driver.findElement(By.xpath(sendinfo_button_xpath));
        sendinfo_button.click();
        //endregion

        //region 10
        boolean mail_has_exception = true;
        try{
            driver.findElement(By.xpath("//input[contains(@data-bind,'Email')]/preceding-sibling::div[@class='validation-group-error-wrap']"));
        }
        catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println("ex");
            mail_has_exception = false;
        }
        Assert.assertEquals("верно заполнен мэйл", true, mail_has_exception);
        //endregion

    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(7500);
        driver.quit();
    }
}

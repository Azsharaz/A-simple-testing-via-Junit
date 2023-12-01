package Homework;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.TestBase;

public class Batch189TestOtomasyonFormu extends TestBase {

    /*
8. inci maddede belirtilen test senaryosunun extent html reporter kullanarak html raporunu alin
9. Extent html reportunuzun sayfa basliginin ve test isminin sizin belirttiginiz isimler oldugunu otomasyon ile test edin
     */
    @Test
    public void title() {

        //  Sayfa başlığının "Batch 189 Selenium" olarak görüntülenip görüntülenmediğini test edin.
        driver.get("https://gleeful-lamington-a9d9de.netlify.app/");
        driver.getTitle();
        Assert.assertEquals("Batch 189 Selenium",driver.getTitle());
    }

    Faker faker=new Faker();
    @Test
    public void verifyLoginMessage() {
        //"Kullanıcı Adı" ve "Şifre" alanlarına faker ile değer girin ve formu gönderin.
        // Başarılı mesajının ("Merhaba [Kullanıcı Adı]! İşlem başarılı.") olarak girilen bilgilerin görüntülenip görüntülenmediğini kontrol edin.

        driver.get("https://gleeful-lamington-a9d9de.netlify.app/");
        WebElement username=driver.findElement(By.id("username"));
        username.sendKeys(faker.name().username());
        String usernameAsText= username.getAttribute("value");
        WebElement password= driver.findElement(By.id("password"));
        password.sendKeys(faker.internet().password());
        WebElement submit= driver.findElement(By.xpath("//input[@type='submit']"));
        submit.click();
        String actualMessage= driver.findElement(By.id("messageBox")).getText();
        String expectedMessage="Merhaba "+usernameAsText+"! İşlem başarılı.";
        Assert.assertEquals(expectedMessage,actualMessage);

    }

    @Test
    public void verifyUnableToLoginBlankUsernamePassword() {
        //  "Kullanıcı Adı" ve "Şifre" alanlarını boş bırakın ve formu göndermeye çalışın. İslemin basarili bir sekilde gerceklesmedigini dogrulayin.
        driver.get("https://gleeful-lamington-a9d9de.netlify.app/");

            WebElement username = driver.findElement(By.id("username"));
            Assert.assertTrue(username.getAttribute("value").isEmpty());

            WebElement password = driver.findElement(By.id("password"));
            Assert.assertTrue(password.getAttribute("value").isEmpty());
        WebElement submit= driver.findElement(By.xpath("//input[@type='submit']"));
        submit.click();
        WebElement actualMessage= driver.findElement(By.id("messageBox"));
        Assert.assertFalse(actualMessage.isDisplayed());

    }

    @Test
    public void radioButtonCanBeSelected() {
        // Cinsiyet radio buttonlarından birini (örneğin "Erkek") seçin ve Seçimin başarılı bir şekilde yapıldığını doğrulayın.

        driver.get("https://gleeful-lamington-a9d9de.netlify.app/");
        WebElement maleRadioButton= driver.findElement(By.id("male"));
        maleRadioButton.click();
        Assert.assertTrue(maleRadioButton.isSelected());

    }


    @Test
    public void selectCountryDropdown() {
        // "Ülke" drop-down menüsünden bir ülkeyi (örneğin "Türkiye") seçin. Seçimin başarılı bir şekilde yapıldığını doğrulayın.

        driver.get("https://gleeful-lamington-a9d9de.netlify.app/");
        WebElement countryDropdown= driver.findElement(By.id("country"));
        Select options=new Select(countryDropdown);
        options.selectByVisibleText("Türkiye");

        Assert.assertEquals(options.getFirstSelectedOption().getText(), "Türkiye");

    }


    @Test
    public void selectMultipleClassesDropdown() {
     //   "Dersler" bölümünden birden fazla ders seçin (örneğin "Java" ve "SQL"). Seçimlerin başarılı bir şekilde yapıldığını doğrulayın.
        driver.get("https://gleeful-lamington-a9d9de.netlify.app/");
        WebElement sdlcWebEle= driver.findElement(By.id("sdlc"));
        sdlcWebEle.click();
        WebElement javaWebEle= driver.findElement(By.id("java"));
        javaWebEle.click();
        Assert.assertTrue(javaWebEle.isSelected());
        Assert.assertTrue(sdlcWebEle.isSelected());

    }

    @Test
    public void playVideoPauseAndSubmitIsEnabled() {
       // Sayfadaki Videoyu baslatin, daha sonra durdurun, daha sonra gönder butonunun etkilesime acik oldugunu doğrulayın.
        driver.get("https://gleeful-lamington-a9d9de.netlify.app/");
        driver.switchTo().frame(0);
        WebElement player= driver.findElement(By.xpath("//*[@id='player']"));
        player.click();
        WebElement pause=driver.findElement(By.xpath("//button[@aria-label='Duraklat klavye kısayolu k']"));
        pause.click();
        driver.switchTo().parentFrame();
        WebElement submit= driver.findElement(By.xpath("//input[@type='submit']"));
        Assert.assertTrue(submit.isEnabled());

    }

    @Test
    public void writeCommentAndSubmit() {

        //"Yorumlar" alanına bir yorum yazin ve yazinin basarili bir sekilde gönderildigini doğrulayin (JSEXECUTOR).

        driver.get("https://gleeful-lamington-a9d9de.netlify.app/");
        WebElement commentBox =driver.findElement(By.id("comments"));
        JavascriptExecutor jse= (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value='hello world'",commentBox);
        String valueOfCommentBox=jse.executeScript("return document.getElementById('comments').value").toString();
        Assert.assertEquals(valueOfCommentBox,"hello world");

    }

    





}

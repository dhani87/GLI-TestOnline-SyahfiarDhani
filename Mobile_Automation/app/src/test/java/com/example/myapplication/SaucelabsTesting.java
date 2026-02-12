package com.example.myapplication;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SaucelabsTesting {
    public AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {

        String projectPath = System.getProperty("user.dir");
        File appDir = new File(projectPath, "src/test/resources");
        File app = new File(appDir, "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554") // Pastikan sesuai dengan nama di 'adb devices'
                .setApp(app.getAbsolutePath())
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity("com.swaglabsmobileapp.MainActivity");

        // Menghubungkan ke Appium Server
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void purchaseScenario() throws InterruptedException {
        // 1. Step Login Aplikasi
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 2. Step Menambahkan Produk 1: Sauce Labs Bike Light
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(1)")).click();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(50));

        // 3. Step Menambahkan Produk 2: Sauce Labs Backpack
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(0)")).click();

        // 4. Step Pergi ke Keranjang
        driver.findElement(AppiumBy.accessibilityId("test-Cart")).click();

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"test-CHECKOUT\"))"
        )).click();

        // 5. Step Proses Checkout
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(120));
        driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT")).click();
        Thread.sleep(2000);

        // 6. Step Isi Informasi Pengiriman

        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(200));
        driver.findElement(AppiumBy.accessibilityId("test-First Name")).sendKeys("Syahfiar");
        driver.findElement(AppiumBy.accessibilityId("test-Last Name")).sendKeys("Dhani");
        driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code")).sendKeys("12920");
        driver.findElement(AppiumBy.accessibilityId("test-CONTINUE")).click();

        // 7. Step menyelesaikan Pembelian
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"test-FINISH\"))"
        )).click();

        // 8. Step Verifikasi Pesan Sukses
        String successMessage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']")).getText();
        Assert.assertEquals(successMessage, "THANK YOU FOR YOU ORDER");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
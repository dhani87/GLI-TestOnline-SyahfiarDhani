package com.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;

public class StepDefinitions {
    AndroidDriver driver;
    WebDriverWait wait;

    @Given("saya membuka aplikasi Alfagift")
    public void saya_membuka_aplikasi_alfagift() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
            .setPlatformName("Android")
            .setAutomationName("UiAutomator2")   
            .setDeviceName("RR8T402J5HT") 
            .setAppPackage("com.alfamart.alfagift")
            .setAppActivity("com.alfamart.alfagift.screen.main.dashboard.DashboardActivity")
           //.setAppActivity("com.alfamart.alfagift.main.MainActivity")
            .setNoReset(true) 
            .setFullReset(false)
            .setAppWaitDuration(Duration.ofSeconds(30))
            .setNewCommandTimeout(Duration.ofSeconds(180));

        // Driver ke Appium Server
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
    
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @And("saya berada di halaman beranda")
    public void saya_berada_di_halaman_beranda() {
        // Menunggu sampai elemen search bar muncul sebagai tanda sudah di homepage
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.alfamart.alfagift:id/et_search")));
        WebElement labelBeranda = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.id("com.alfamart.alfagift:id/tv_label_homepage")
    ));
    assert(labelBeranda.getText().equals("Beranda"));
    }

    @When("saya mengetik {string} di kolom pencarian")
    public void saya_mengetik_di_kolom_pencarian(String namaProduk) {
        WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.alfamart.alfagift:id/et_search")));
        searchBar.click();
        searchBar.sendKeys(namaProduk);
    }

    @And("saya menekan tombol cari")
    public void saya_menekan_tombol_cari() {
        // Melakukan 'Search' pada keyboard HP nya
        driver.executeScript("mobile: performEditorAction", java.util.Map.of("action", "search"));
    }

    @And("saya klik tombol {string} pada produk pertama yang muncul")
    public void saya_klik_tombol_tambah(String tombol) {
        // Menunggu produk muncul dan klik tombol Tambah ke Keranjang
        WebElement btnTambah = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.alfamart.alfagift:id/btn_add_to_cart")));
        btnTambah.click();
    }

    @Then("produk tersebut berhasil ditambahkan ke keranjang belanja")
    public void produk_berhasil_ditambahkan() {
        WebElement keranjang = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.alfamart.alfagift:id/txt_count_item_basket")));
        
        //Mengambil teks angka
        String count = keranjang.getText();

        //Verifikasi bahwa item sudah berhasil ditambahkan dan 
        //pada logo keranjang sudah muncul angka nya
        assert(Integer.parseInt(count) > 0);
        System.out.println("Jumlah barang di keranjang: " + count);
    }

    @After
     public void proses_selesai() {
         if (driver != null) {
            driver.quit();
        }
     }
       
}
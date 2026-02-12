package com.automation.test;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.controller.PersonController;
import com.automation.model.PersonResponse;

public class ApiTesting {
    PersonController controller = new PersonController();

    @Test
    public void verifyFakerApiTest() {
        int ExpectedQuantity = 10;
        String ExpectedGender = "male";

        // Call Controller
        PersonResponse response = controller.getPersons(ExpectedQuantity, ExpectedGender, "1990-01-01", "2000-12-31");

        //Verify 1 : Total data harus 10
        Assert.assertEquals(response.data.size(), ExpectedQuantity, "Jumlah data tidak sesuai ketentuan!");

        response.data.forEach(person -> {
            //Verify 2 : Gender harus sesuai request (male)
            Assert.assertEquals(person.gender, ExpectedGender, "Gender tidak sesuai request!");

            //Verify 3 : Tahun lahir harus di antara 1990 - 2000
            LocalDate date = LocalDate.parse(person.birthday);
            Assert.assertTrue(date.getYear() >= 1990 && date.getYear() <= 2000,
                "Tahun lahir di luar rentang: " + person.birthday);
        });

        System.out.println("Semua pengujian API berhasil lolos!");
    }
}

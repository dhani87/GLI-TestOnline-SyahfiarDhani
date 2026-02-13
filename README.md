# GLI-TestOnline-SyahfiarDhani
berisikan jawaban dari test online GLI posisi QA Automation

Dokumentasi Hasil pengetesan dari Automation Test ini sudah saya sertakan pada link drive berikut: https://drive.google.com/drive/folders/1N8PCWUDKI4MLQBYXuXQPdew7lK21L7qL?usp=sharing


------Soal 1 Mobile Automation------

Persiapan

1. Ambil source code dari repository GitHub berikut -> https://github.com/dhani87/GLI-TestOnline-SyahfiarDhani.git atau git@github.com:dhani87/GLI-TestOnline-SyahfiarDhani.git 
2. Buka Projek di Android Studio

Eksekusi

1. Jalankan server appium -> appium
2. Jalankan emulator
3. Jalankan Run Test pada purchaseScenario

Catatan

1. File APK Saucelabs sudah saya sertakan di dalam folder -> src/test/resources

------Soal 2 API Automation------

Persiapan

1. Ambil source code dari repository GitHub berikut -> https://github.com/dhani87/GLI-TestOnline-SyahfiarDhani.git atau git@github.com:dhani87/GLI-TestOnline-SyahfiarDhani.git 
2. Buka di IDE (Visual Studio Code) dan pastikan anda membuka folder yang berisi pom.xml

Verifikasi Data

Pastikan file yang ada di dalam folder sudah sesuai untuk package nya:
1. src/main/java/com/automation/model/PersonResponse.java & PersonData.java
2. src/main/java/com/automation/controller/PersonController.java
3. src/test/java/com/automation/test/ApiTesting.java

Eksekusi

-> Melalui Terminal

1. Jalankan: mvn clean install -U
2. Jalankan: mvn test -Dtest=ApiTesting

-> Melalui IDE

1. Pada Visual Studio Code, Pastikan anda sudah open folder faker-api-test bukan open folder API-Automation
2. Kalau sudah Buka File Apitesting.java
3. Jalankan Test Skenario dengan cara klik icon play disamping @Test
Atau klik kanan pada icon ceklis lalu pilih run test

------Soal 3 Alfagift Automation------

Persiapan

1. Ambil source code dari repository GitHub berikut -> https://github.com/dhani87/GLI-TestOnline-SyahfiarDhani.git atau git@github.com:dhani87/GLI-TestOnline-SyahfiarDhani.git 
2. Buka di IDE (Visual Studio Code) dan pastikan anda membuka folder yang berisi pom.xml

Verifikasi Data

Pastikan file yang ada di dalam folder sudah sesuai untuk package nya:
1. src/test/java/com/automation/StepDefinitions.java
2. src/test/java/com/automation/TestRunner.java
3. src/test/resources/features/search_product.feature

Konfigurasi Device

1. Buka file di src/test/java/com/automation/StepDefinitions.java dan sesuaikan deviceName dengan ID HP anda menggunakan perintah adb devices

Eksekusi

1. Hubungkan Android dan pastikan USB Debugging nya aktif
2. Jalankan server appium -> appium
3. pada Terminal proyek jalankan -> mvn clean test




Feature: Keranjang Belanja Alfagift

  Scenario: Menambahkan produk ke keranjang melalui fitur pencarian
    Given saya membuka aplikasi Alfagift
    And saya berada di halaman beranda
    When saya mengetik "Susu" di kolom pencarian
    And saya menekan tombol cari
    And saya klik tombol "+ Keranjang" pada produk pertama yang muncul
    Then produk tersebut berhasil ditambahkan ke keranjang belanja
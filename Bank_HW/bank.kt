var bakiye = 18000.0
var sifre = "1453"
val islemGecmisi = mutableListOf<String>()

fun main() {

    println("=== NAZLI BANK ATM'YE HOŞ GELDİNİZ ===")

    if (!girisYap()) {
        println("Kartınız bloke edildi.")
        return
    }

    var devam = true

    while (devam) {

        val secim = menuSecimAl()

        when (secim) {
            1 -> bakiyeGoster()
            2 -> paraYatir()
            3 -> paraCek()
            4 -> paraTransferi()
            5 -> {
                val yenidenGiris = sifreDegistir()

                if (yenidenGiris) {
                    if (!girisYap()) {
                        println("Kartınız bloke edildi.")
                        return
                    }
                }
            }
            6 -> gecmisGoster()
            7 -> {
                println("Kartınız iade ediliyor...")
                return
            }
        }

        devam = devamEtmekIsterMi()
    }

    println("İyi günler dileriz...")
}

fun girisYap(): Boolean {
    var hak = 3

    while (hak > 0) {
        print("Şifrenizi giriniz: ")
        val girilen = readLine()

        if (girilen == sifre) {
            println("✔ Giriş başarılı.")
            return true
        } else {
            hak--
            println("Hatalı şifre! Kalan hak: $hak")
        }
    }
    return false
}

fun menuSecimAl(): Int {
    while (true) {
        println("\n--- ANA MENÜ ---")
        println("1- Bakiye Görüntüleme")
        println("2- Para Yatırma")
        println("3- Para Çekme")
        println("4- Para Transferi")
        println("5- Şifre Değiştirme")
        println("6- İşlem Geçmişi")
        println("7- Çıkış")
        print("Seçiminiz: ")

        val secim = readLine()?.toIntOrNull()

        if (secim != null && secim in 1..7) {
            return secim
        } else {
            println("Geçersiz seçim! Tekrar deneyin.")
        }
    }
}

fun bakiyeGoster() {
    println("\n Bakiyeniz: $bakiye TL")
    islemGecmisi.add("Bakiye görüntülendi")
}

fun paraYatir() {
    print("Yatırılacak tutar: ")
    val miktar = readLine()?.toDoubleOrNull()

    if (miktar != null && miktar > 0) {
        bakiye += miktar
        islemGecmisi.add("Para yatırıldı: $miktar TL")

        println("✔ Para yatırma işlemi başarılı.")
        println(" Güncel bakiyeniz: $bakiye TL")
    } else {
        println("Hatalı tutar!")
    }
}

fun paraCek() {
    print("Çekilecek tutar: ")
    val miktar = readLine()?.toDoubleOrNull()

    if (miktar == null || miktar <= 0) {
        println("Geçersiz tutar!")
        return
    }

    if (miktar > bakiye) {
        println("Yetersiz bakiye!")
        return
    }

    bakiye -= miktar
    islemGecmisi.add("Para çekildi: $miktar TL")

    println("✔ Lütfen paranızı alınız.")
    println("Kalan bakiyeniz: $bakiye TL")
}

fun paraTransferi() {
    print("Alıcı IBAN: ")
    val iban = readLine()

    print("Gönderilecek tutar: ")
    val miktar = readLine()?.toDoubleOrNull()

    if (iban == null || iban.length < 5) {
        println("Geçersiz IBAN!")
        return
    }

    if (miktar == null || miktar <= 0 || miktar > bakiye) {
        println("İşlem başarısız!")
        return
    }

    bakiye -= miktar
    islemGecmisi.add("Transfer: $miktar TL -> $iban")

    println("✔ Transfer işlemi başarılı.")
    println("Kalan bakiyeniz: $bakiye TL")
}

fun sifreDegistir(): Boolean {
    print("Mevcut şifre: ")
    val eski = readLine()

    if (eski == sifre) {
        print("Yeni şifre: ")
        val yeni = readLine()

        if (yeni == null || yeni.isEmpty()) {
            println("Geçersiz yeni şifre!")
            return false
        }

        if (yeni == sifre) {
            println("Yeni şifre, mevcut şifre ile aynı olamaz!")
            return false
        }

        sifre = yeni
        println("✔ Şifre başarıyla değiştirildi.")
        println("Güvenlik nedeniyle tekrar giriş yapmanız gerekiyor.")
        return true

    } else {
        println("Şifre hatalı!")
        return false
    }
}

fun gecmisGoster() {
    println("\n--- İŞLEM GEÇMİŞİ ---")

    if (islemGecmisi.isEmpty()) {
        println("Henüz işlem yok.")
    } else {
        for (islem in islemGecmisi) {
            println(islem)
        }
    }
}

fun devamEtmekIsterMi(): Boolean {
    while (true) {
        println("\n[ < Menü ]           [ Çıkış > ]")
        print("Seçiminiz: ")

        val secim = readLine()

        when (secim) {
            "<" -> return true
            ">" -> {
                println("Kartınız iade ediliyor...")
                return false
            }
            else -> println("Lütfen sadece < veya > giriniz.")
        }
    }
}
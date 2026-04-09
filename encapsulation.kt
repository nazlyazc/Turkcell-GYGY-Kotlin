// Encapsulation (Kapsülleme) örneği - Ürün fiyat sistemi

class Product(private var price: Double) {

    // Fiyatı görüntüleme
    fun getPrice(): Double {
        return price
    }

    // Fiyat güncelleme (kontrollü)
    fun setPrice(newPrice: Double) {
        if (newPrice > 0) {
            price = newPrice
            println("Fiyat güncellendi")
        } else {
            println("Fiyat 0'dan büyük olmalı!")
        }
    }

    // İndirim uygulama
    fun applyDiscount(percent: Int) {
        if (percent in 1..100) {
            price -= price * percent / 100
            println("%$percent indirim uygulandı")
        } else {
            println("Geçersiz indirim oranı!")
        }
    }
}

fun main() {
    val product = Product(100.0)

    println("Fiyat: ${product.getPrice()} TL")

    product.setPrice(150.0)
    println("Yeni fiyat: ${product.getPrice()} TL")

    product.applyDiscount(20)
    println("İndirimli fiyat: ${product.getPrice()} TL")

    product.setPrice(-50.0) // hatalı giriş
}
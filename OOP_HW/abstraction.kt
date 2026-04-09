// Soyut sınıf
abstract class Character {

    // Her karakter saldırmak zorunda ama nasıl olduğu farklı
    abstract fun attack()

    fun walk() {
        println("Karakter yürüyor")
    }
}

// Alt sınıf - Savaşçı
class Warrior : Character() {

    override fun attack() {
        println("Kılıç ile saldırdı")
    }
}

// Alt sınıf - Büyücü
class Mage : Character() {

    override fun attack() {
        println("Büyü yaparak saldırdı")
    }
}

fun main() {
    val c1: Character = Warrior()
    val c2: Character = Mage()

    c1.walk()
    c1.attack()

    println("------")

    c2.walk()
    c2.attack()
}
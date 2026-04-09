// Inheritance (Kalıtım) örneği - Çalışan sistemi

// Ana sınıf
open class Employee(val name: String, val salary: Double) {

    fun info() {
        println("İsim: $name, Maaş: $salary")
    }
}

// Alt sınıf
class Engineer(name: String, salary: Double) : Employee(name, salary) {

    fun code() {
        println("$name kod yazıyor")
    }
}

// Alt sınıf
class Manager(name: String, salary: Double) : Employee(name, salary) {

    fun manage() {
        println("$name ekip yönetiyor")
    }
}

fun main() {
    val engineer = Engineer("Nazlı", 30000.0)
    val manager = Manager("Ahmet", 40000.0)

    engineer.info()   // üst sınıftan geldi
    engineer.code()   // kendi fonksiyonu

    println("------")

    manager.info()    // üst sınıftan geldi
    manager.manage()  // kendi fonksiyonu
}
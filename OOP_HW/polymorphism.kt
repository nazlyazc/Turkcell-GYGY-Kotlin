open class Sensor(val id: String) {

    open fun readData() {
        println("Sensor $id veri okuyor")
    }
}

class TemperatureSensor(id: String) : Sensor(id) {

    override fun readData() {
        println("TemperatureSensor $id sıcaklık verisi okuyor: 25°C")
    }
}

class PressureSensor(id: String) : Sensor(id) {

    override fun readData() {
        println("PressureSensor $id basınç verisi okuyor: 101 kPa")
    }
}

fun main() {
    val s1: Sensor = TemperatureSensor("T1")
    val s2: Sensor = PressureSensor("P1")

    s1.readData() // TemperatureSensor’un readData çalışır
    s2.readData() // PressureSensor’un readData çalışır
}
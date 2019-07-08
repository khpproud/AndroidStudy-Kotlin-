package ch06.property

// 클래스의 위임 사용하기
interface Car {
    fun go(): String
}

class VanImpl(val power: String) : Car {
    override fun go(): String = "은 짐을 적재하며 $power 을 가집니다."
}

class SportImpl(val power: String) : Car {
    override fun go(): String = "은 경주용에 사용되며 $power 을 가집니다."
}

class CarModel(val model: String, impl: Car) : Car by impl {
    fun carInfo() {
        println("$model ${go()}")               // 참조 없이 각 인터페이스 구현 클래스의 go() 에 접근
    }
}

fun main() {
    val myDamas = CarModel("Damas 2010", VanImpl("100 마력"))
    val my350z = CarModel("350z 2008", SportImpl("350 마력"))

    myDamas.carInfo()                           // CarInfo 에 대한 다형성을 나타냄
    my350z.carInfo()
}
package ch04_flowcontrol

// when 문을 가용한 점수 등급 구하기
fun main() {
    print("Enter the score : ")
    val score = readLine()!!.toDouble()
    var grade: Char = 'F'

    when (score) {
        in 90.0.. 100.0 -> grade = 'A'
        in 80.0.. 89.9 -> grade = 'B'
        in 70.0.. 79.9 -> grade = 'C'
        !in 70.0.. 100.0 -> grade = 'F'
    }
    println("Score : $score, Grade : $grade")
}
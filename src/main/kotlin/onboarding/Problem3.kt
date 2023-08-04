package onboarding

fun solution3(number: Int): Int {
    var answer = 0

    for (i in 1..number) {
        answer += getClapCount(i)
    }

    return answer
}

fun getClapCount(number: Int): Int {
    val intList = number.toString().toList().map { it.digitToInt() }

    var count = 0
    intList.forEach{
        when (it) {
            3 -> count++
            6 -> count++
            9 -> count++
            else -> Unit
        }
    }

    return count
}

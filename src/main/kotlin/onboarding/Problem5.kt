package onboarding

fun solution5(money: Int): List<Int> {
    var money = money
    var answer: MutableList<Int> = mutableListOf()
    val bills = listOf(50_000, 10_000, 5_000, 1_000, 500, 100, 50, 10, 1)

    for (i in bills.indices) {
        answer.add(money / bills[i])
        money %= bills[i]
    }

    return answer
}
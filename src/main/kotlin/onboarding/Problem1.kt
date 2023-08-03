package onboarding

import java.lang.Integer.max

fun solution1(pobi: List<Int>, crong: List<Int>): Int {
    if (!validBook(pobi, crong)) {
        return -1
    }

    return getResult(pobi, crong)
}

fun validBook(pobi: List<Int>, crong: List<Int>): Boolean {
    if (pobi.any { it !in 1..400 } || crong.any { it !in 1..400 } ) {
        return false
    }

    if (pobi.size != 2 || crong.size != 2) {
        return false
    }

    if (pobi[0] % 2 != 1 || pobi[1] % 2 != 0) {
        return false
    }

    if (crong[0] % 2 != 1 || crong[1] % 2 != 0) {
        return false
    }

    if (pobi[1] != pobi[0] + 1) {
        return false
    }

    if (crong[1] != crong[0] + 1) {
        return false
    }

    return true
}

fun getResult(pobi: List<Int>, crong: List<Int>): Int {
    val pobiScore = getScore(pobi[0], pobi[1])
    val crongScore = getScore(crong[0], crong[1])

    return when {
        pobiScore == crongScore -> 0
        pobiScore > crongScore -> 1
        pobiScore < crongScore -> 2
        else -> -1
    }
}

fun getScore(leftPage: Int, rightPage: Int): Int {
    val leftPageList = leftPage.toString().map { it.toString().toInt() }
    val rightPageList = rightPage.toString().map { it.toString().toInt() }

    return max(getMaxSumOrMul(leftPageList), getMaxSumOrMul(rightPageList))
}

fun getMaxSumOrMul(pageList: List<Int>): Int {
    return max(pageList.sum(), pageList.reduce { mul, i -> mul * i })
}
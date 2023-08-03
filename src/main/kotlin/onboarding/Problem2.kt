package onboarding

fun solution2(cryptogram: String): String {
    var answer = StringBuilder(cryptogram)

    var removedIndex: Set<Int>
    var removedCount = 0
    do {
        removedIndex = mutableSetOf()
        removedCount = 0

        for(i in 0 until answer.length - 1) {
            if (answer[i] == answer[i + 1]) {
                removedIndex.add(i)
                removedIndex.add(i + 1)
            }
        }

        for(i in removedIndex) {
            answer.deleteAt(i - removedCount)
            removedCount++
        }
    } while(removedCount != 0)

    return answer.toString()
}
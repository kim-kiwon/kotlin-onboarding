package onboarding

fun solution4(word: String): String {
    val sb = java.lang.StringBuilder(word)
    return sb.map { it.greenFrog() }.joinToString("")
}

// map 함수는 List<map반환타입> 으로 변경시킴
fun Char.greenFrog(): Char {
    return when {
        isUpperCase() -> ('Z'.code - (this.code - 'A'.code)).toChar()
        isLowerCase() -> ('z'.code - (this.code - 'a'.code)).toChar()
        else -> this
    }
}
package onboarding

fun solution6(forms: List<List<String>>): List<String> {
    var answerSet: MutableSet<String> = mutableSetOf()
    val crewEmails: List<String> = forms.map { it[0] }
    val crewNicknames: List<String> = forms.map { it[1] }

    forms.forEach { crew ->
        val email = crew[0]
        val nickname = crew[1]

        val partialNicknameList = getPartialNicknames(nickname)
        crewNicknames.forEachIndexed {index, crewNickname ->
            if (crewNickname == nickname) {
                return@forEachIndexed
            }
            partialNicknameList.forEach { partialNickname ->
                if (crewNickname.contains(partialNickname)) {
                    answerSet.add(email)
                    answerSet.add(crewEmails[index])
                }
            }

        }
    }

    return answerSet.sorted()
}

fun getPartialNicknames(nickname: String): List<String> {
    var partialNicknameList:MutableList<String> = mutableListOf()

    if (nickname.length == 1) {
        return partialNicknameList
    }

    for (i in 0 until nickname.length - 1) {
        partialNicknameList.add("${nickname[i]}${nickname[i+1]}")
    }

    return partialNicknameList
}
package onboarding

fun solution7(
    user: String,
    friends: List<List<String>>,
    visitors: List<String>
): List<String> {
    val answerMap: MutableMap<String, Int> = mutableMapOf()
    val friendsMap = getFriendsMap(friends) // 각 유저의 친구 List 를 갖고 있는 Map 구성

    giveSameFriendsScore(answerMap, friendsMap, user) // 동일한 친구를 가진 유저에게 점수부여
    giveVisitScore(answerMap, visitors) // 방문 유저에게 점수부여

    return answerMap.toList()
        .sortedWith(compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first }) // 점수기준 내림차순. 이름기준 오름차순 정렬.
        .map { it.first }
        .filter {
            friendsMap[user]?.contains(it) == false // 기존 친구유저 제외
        }
}

fun getFriendsMap(friends: List<List<String>>): Map<String, List<String>> {
    var friendsMap: MutableMap<String, MutableList<String>> = mutableMapOf()

    friends.forEach {
        friendInfo ->
            val (userA, userB) = friendInfo
            addToFriendsMap(friendsMap, userA, userB)
            addToFriendsMap(friendsMap, userB, userA)
    }

    return friendsMap
}

fun addToFriendsMap(friendsMap: MutableMap<String, MutableList<String>>, user: String, friend: String) {
    if (friendsMap[user] == null) {
        friendsMap[user] = mutableListOf(friend)
        return
    }
    friendsMap[user]?.add(friend)
}

fun giveSameFriendsScore(answerMap: MutableMap<String, Int>, friendsMap: Map<String, List<String>>, user: String) {
    friendsMap.forEach { (userName, friendList) ->
        if (user == userName) return@forEach

        friendList.forEach { friendName ->
            if (friendsMap[user]?.contains(friendName) == true) {
                answerMap[userName] = (answerMap[userName] ?: 0) + 10
            }
        }
    }
}

fun giveVisitScore(answerMap: MutableMap<String, Int>, visitors: List<String>) {
    visitors.forEach { visitUser ->
        answerMap[visitUser] = (answerMap[visitUser] ?: 0) + 1
    }
}

// 1. Map <Key: 유저명, Value: 친구목록> 구현
// 2. AnswerMap 구현
//    다른 유저 순회하며 user 와 겹치는 친구수만큼 AnswerMap 에서 해당 대상+=10
//    이때 벌써 해당 유저와 친구라면 제외
// 3. visitors 목록 순회하며. AnswerMap 에서 ++.

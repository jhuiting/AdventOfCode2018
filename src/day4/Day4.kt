package day4

import util.InputUtils

class SleepingGuardSolver {
    companion object {
        fun solvePart1(guardSleepRanges: Map<Int, List<Pair<String, List<Int>>>>) {

            val mostAsleepGuard = guardSleepRanges.map { sleepRange -> sleepRange.key to sleepRange.value.sumBy { it.second.sum() } }.sortedByDescending { it.second }.first().first
            val mostAsleepDuring = guardSleepRanges[mostAsleepGuard]!!.map { it.second }.flatten().groupBy { it }.map { it.value }.maxBy { it.size }?.first()

            println("Solution 1: Most asleep guard $mostAsleepGuard is most asleep during minute $mostAsleepDuring. Checksum = ${mostAsleepGuard * mostAsleepDuring!!}")
        }

        fun solvePart2(guardSleepRanges: Map<Int, List<Pair<String, List<Int>>>>) {

            val mostAsleepMinutePerGuard = guardSleepRanges.map { sleepRange ->
                sleepRange.key to sleepRange.value.map { it.second }.flatten().groupBy { it }.maxBy { it.value.size }
            }.maxBy { it.second!!.value.size }

            val guardId = mostAsleepMinutePerGuard!!.first
            val minute = mostAsleepMinutePerGuard.second!!.value.first()

            println("Solution2: Guard $guardId is most asleep during minute $minute. Checksum = ${guardId * minute}")
        }
    }
}


fun main(args : Array<String>) {
    val guardLogs = InputUtils.getInputAsString("day4")
        .split("\n")
        .sorted()

    var currentGuard = ""
    val seenValues = mutableListOf<Pair<String, Int>>()
    for (guardLog in guardLogs) {
        val guardMatch = """.*Guard #(\d*) begins shift""".toRegex().find(guardLog)
        if (guardMatch?.value.orEmpty() !== "") {
            currentGuard = guardMatch!!.groupValues[1]
        }

        val sleepOrAwakeMatch = """.*\d{2}:(\d{2})] (falls asleep|wakes up)""".toRegex().find(guardLog)
        if (sleepOrAwakeMatch?.value.orEmpty() !== "") {
            seenValues.add(currentGuard to sleepOrAwakeMatch!!.groups[1]?.value?.toInt()!!)
        }
    }

    val guardSleepRanges = seenValues.chunked(2).map { it[0].first to (it[0].second..it[1].second).toList() }.groupBy { it.first.toInt() }

    SleepingGuardSolver.solvePart1(guardSleepRanges)
    SleepingGuardSolver.solvePart2(guardSleepRanges)
}
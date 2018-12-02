package day2

import util.InputUtils

fun String.difference(otherString: String): Int {
    return this.zip(otherString).count { it.first != it.second }
}

class BoxCodeSolver {
    companion object {
        fun solvePart1(boxIds: List<String>) {
            val groupedBoxIds = boxIds.map { boxId ->
                boxId
                    .toCharArray()
                    .toList()
                    .groupingBy { it }
                    .eachCount()
            }

            val boxesWithTwoLetterCodes = groupedBoxIds.filter { groupBoxId -> groupBoxId.containsValue(2) }
            val boxesWithThreeLetterCodes = groupedBoxIds.filter { groupBoxId -> groupBoxId.containsValue(3) }

            println("Solution for part 1: Checksum == ${boxesWithTwoLetterCodes.size * boxesWithThreeLetterCodes.size}")
        }

        fun solvePart2(boxIds: List<String>) {
            val boxIdDifference = boxIds
                .flatMap { i -> boxIds.map { i to it } }
                .first { boxIdPair -> boxIdPair.first.difference(boxIdPair.second) == 1 }

            val boxValue = boxIdDifference.first.zip(boxIdDifference.second)
                                                                         .filter { it.first == it.second }
                                                                         .unzip()

            println("Solution frequency part 2: ${boxValue.first.joinToString( "") }")
        }
    }
}

fun main(args : Array<String>) {
    val boxIds = InputUtils.getInputAsString("day2")
                        .split("\n")

    BoxCodeSolver.solvePart1(boxIds)
    BoxCodeSolver.solvePart2(boxIds)
}
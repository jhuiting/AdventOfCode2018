package day1

import util.InputUtils

fun <T> Sequence<T>.repeatSequence() = sequence { while (true) yieldAll(this@repeatSequence) }

class FrequencySolver {
    companion object {
        fun solvePart1(frequencies: List<Int>) {
            val totalCount = 0 + frequencies.reduce { totalValue, currentValue ->  totalValue + currentValue }

            println("Solution frequency part 1: $totalCount")
        }

        fun solvePart2(frequencies: List<Int>) {
            var totalValue = 0
            val previousFrequencies = mutableMapOf(0 to 0)

            val frequencySequence = frequencies.asSequence().repeatSequence()
            for (currentValue in frequencySequence) {
                val frequency = totalValue + currentValue
                if (previousFrequencies.containsKey(frequency)) {
                    println("Solution frequency part 2: $frequency")
                    return
                } else {
                    totalValue+= currentValue
                    previousFrequencies[frequency] = 0
                }
            }
        }
    }
}

fun main(args : Array<String>) {
    val frequencies = InputUtils.getInputAsString("day1")
                        .split("\n")
                        .map { it.toInt() }

    FrequencySolver.solvePart1(frequencies)
    FrequencySolver.solvePart2(frequencies)
}
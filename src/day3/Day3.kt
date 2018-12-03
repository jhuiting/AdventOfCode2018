package day3

import util.InputUtils

class LandClaimSolver {
    companion object {
        fun solvePart1(allPoints: List<String>) {
            val duplicateValues = allPoints.groupingBy { it }.eachCount().filterValues { it >= 2 }.count()

            println("Solution for part 1: $duplicateValues")
        }

        fun solvePart2(claims: List<ClaimWrapper>, allPoints: List<String>) {
            val uniquePoints = allPoints.groupingBy { it }.eachCount().filterValues { it == 1 }
            val uniqueClaim = claims.map(::claimToAllPoints).first { it.all { uniquePoints.contains(it)  }}

            println("Solution for part 2: Unique claim lies between ${uniqueClaim.first()} and ${uniqueClaim.last()}")
        }
    }
}

data class Point<T>(var x: T, var y: T)
data class ClaimWrapper (val coordinate: Point<Int>, val size: Point<Int>)

fun claimToAllPoints(claim: ClaimWrapper): List<String> {
    return (claim.coordinate.x..(claim.coordinate.x + claim.size.x - 1)).flatMap { xValue ->
        (claim.coordinate.y..(claim.coordinate.y + claim.size.y - 1)).map { yValue -> "($xValue,$yValue)" }
    }
}

fun main(args : Array<String>) {
    val claims = InputUtils.getInputAsString("day3")
                        .split("\n")
                        .map {
                            ClaimWrapper(
                                """(\d*,\d*)""".toRegex().find(it)!!.value.split(",").let {
                                    Point(it[0].toInt(), it[1].toInt())
                                },
                                """(\d*x\d*)""".toRegex().find(it)!!.value.split("x").let {
                                    Point(it[0].toInt(), it[1].toInt())
                                }
                            )
                        }

    val allPoints = claims.flatMap(::claimToAllPoints)

    LandClaimSolver.solvePart1(allPoints)
    LandClaimSolver.solvePart2(claims, allPoints)
}
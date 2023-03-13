package valdev.free.kmeans.algo

import android.util.Log
import java.util.*
import kotlin.math.sqrt
import kotlin.random.Random

class Vector(val dimension: Int, vararg initialValues: Double) {
    val contents: DoubleArray = DoubleArray(dimension) { 0.0 }

    init {

        if (initialValues.isEmpty()) {
            repeat(dimension) { i ->
                contents[i] = Random.nextDouble() * 100
            }

        } else {
            initialValues.forEachIndexed { index, value ->
                contents[index] = value
            }
        }
    }

    fun distance(otherVec: Vector): Double {
        if (otherVec.dimension != dimension) throw IllegalArgumentException("False dimension")

        return sub(otherVec).abs()
    }

    fun sub(otherVec: Vector): Vector {
        if (otherVec.dimension != dimension) throw IllegalArgumentException("False dimension")

        val resultVecContents = contents.copyOf(dimension)

        repeat(dimension) { i ->
            resultVecContents[i] -= otherVec.contents[i]
        }

        return Vector(dimension, *resultVecContents)
    }

    fun abs(): Double {
        var result = 0.0
        contents.forEach { x ->
            result += x * x
        }
        return sqrt(result)
    }

    override fun toString(): String {
        return contents.contentToString()
    }
}
package valdev.free.kmeans.algo

import java.util.LinkedList

class Cluster(var centerPoint: Vector) {
    val dataPoints = LinkedList<Vector>()

    fun calculateMean(): Vector {
        val dimension = centerPoint.dimension
        val resultVector = Vector(dimension, 0.0)

        repeat(dimension) { i ->
            var sum = 0.0
            dataPoints.forEach {
                sum += it.contents[i]
            }
            resultVector.contents[i] = (sum / dimension)

        }
        return resultVector
    }

    fun doNextIteration() {
        centerPoint = calculateMean()
        dataPoints.clear()
    }

}
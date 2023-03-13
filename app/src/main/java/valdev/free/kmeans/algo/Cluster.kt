package valdev.free.kmeans.algo

import java.util.*

class Cluster(var centerPoint: Vector) {
    val dataPoints = LinkedList<Vector>()

    private fun calculateMean(): Vector {
        val dimension = centerPoint.dimension
        val resultVector = Vector(dimension, 0.0)
        if(dataPoints.isEmpty()) return centerPoint

        repeat(dimension) { i ->
            var sum = 0.0
            dataPoints.forEach {
                sum += it.contents[i]
            }
            resultVector.contents[i] = (sum / dataPoints.size)


        }
        return resultVector
    }

    fun doNextIteration() {
        centerPoint = calculateMean()
        dataPoints.clear()
    }




}
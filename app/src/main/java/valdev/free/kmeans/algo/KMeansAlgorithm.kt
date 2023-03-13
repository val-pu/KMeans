package valdev.free.kmeans.algo

import java.util.LinkedList

object KMeansAlgorithm {

    fun doAlgo(
        n: Int,
        dataSet: LinkedList<Vector>,
        dimension: Int,
        iterations: Int = 1
    ): Array<Cluster> {
        val cluster = Array(n) {
            Cluster(
                Vector(
                    dimension,
                    *dataSet.random().contents
                )
            )
        }

        repeat(iterations) {
            cluster.forEach { it.doNextIteration() }

            dataSet.forEach { currentDataPoint ->
                var closestCluster: Cluster? = null
                var closestClusterDistance = Double.MAX_VALUE

                repeat(n) { i ->
                    val currentClusterCentre = cluster[i].centerPoint

                    val distance = currentClusterCentre.distance(currentDataPoint)

                    if (distance < closestClusterDistance) {

                        closestClusterDistance = distance
                        closestCluster = cluster[i]
                    }
                }

                closestCluster?.dataPoints?.add(currentDataPoint)
            }

        }



        return cluster
    }

}
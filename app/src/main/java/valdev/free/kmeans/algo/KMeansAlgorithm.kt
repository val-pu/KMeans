package valdev.free.kmeans.algo

import android.util.Log
import java.util.LinkedList

object KMeansAlgorithm {

    fun doAlgo(
        n: Int,
        dataSet: LinkedList<Vector>,
        dimension: Int,
        iterations: Int = 3
    ): Array<Cluster> {

        var index = 0;

        val cluster = Array(n) {
            val contents = dataSet.random().contents
            Cluster(
                dataSet[++index]
            )
        }

        repeat(iterations) {
            cluster.forEach { it.doNextIteration() }

            dataSet.forEach { currentDataPoint ->
                var closestCluster: Cluster? = null
                var closestClusterDistance = 10000000.0

                repeat(n) { i ->
                    val currentClusterCentre = cluster[i].centerPoint

                    val distance = currentClusterCentre.distance(currentDataPoint)


                    if (distance < closestClusterDistance) {


                        closestClusterDistance = distance
                        closestCluster = cluster[i]
                    }
                }

                closestCluster!!.dataPoints?.add(currentDataPoint)
            }

        }



        return cluster
    }

}
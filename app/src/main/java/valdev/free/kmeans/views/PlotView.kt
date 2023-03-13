package valdev.free.kmeans.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import valdev.free.kmeans.algo.KMeansAlgorithm
import valdev.free.kmeans.algo.Vector
import java.util.LinkedList
import kotlin.random.Random
import kotlin.random.nextInt

class PlotView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private lateinit var canvas: Canvas
    private var bm: Bitmap? = null

    private val colorArray = arrayOf(Color.YELLOW, Color.BLUE, Color.RED, Color.GRAY, Color.LTGRAY, Color.GREEN, Color.CYAN, Color.DKGRAY, Color.MAGENTA)
    private val centroidPaint = Paint()

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (bm != null) return

        centroidPaint.color = Color.BLACK
        centroidPaint.alpha = 90

        bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        background = BitmapDrawable(bm)
        canvas = Canvas(bm!!)
        repeat(15) {
            val maxVariance = (Random.nextDouble(.4)+.1) * width
            val clusterCenterX = Random.nextDouble(width.toDouble())
            val clusterCenterY = Random.nextDouble(height.toDouble())

            repeat(Random.nextInt(20)) {
                data.add(
                    Vector(
                        2,
                        clusterCenterX + Random.nextDouble(maxVariance),
                        clusterCenterY + Random.nextDouble(maxVariance)
                    )
                )

            }

            repeat(0) {
                data.add(
                    Vector(
                        2,
                        Random.nextDouble(width.toDouble()),
                        Random.nextDouble(height.toDouble())
                    )
                )
            }

        }
        val p = Paint()

        val clusters = KMeansAlgorithm.doAlgo(3, data, 2, 5)

        clusters.forEachIndexed { i, cc ->
            val centerPoint = cc.centerPoint
            val cX = centerPoint.contents[0].toFloat()
            val cY = centerPoint.contents[1].toFloat()

            canvas!!.drawCircle(cX, cY, 30F, centroidPaint)


            p.color = colorArray[i]
            cc.dataPoints.forEach {

                val dataPointX = it.contents[0].toFloat()
                val dataPointY = it.contents[1].toFloat()

                canvas.drawLine(cX, cY,dataPointX,dataPointY,centroidPaint)
                canvas!!.drawCircle(dataPointX, dataPointY, 10F, p)
            }

        }
        Log.i("Draw", "D")
        invalidate()
    }

    var drawn = false

    var data = LinkedList<Vector>()
}
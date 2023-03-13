package valdev.free.kmeans.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import valdev.free.kmeans.algo.KMeansAlgorithm
import valdev.free.kmeans.algo.Vector
import java.util.LinkedList
import kotlin.random.Random
class PlotView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private lateinit var canvas: Canvas
    private var bm: Bitmap? = null

    private val colorArray = arrayOf(Color.YELLOW, Color.BLUE, Color.RED, Color.GRAY, Color.LTGRAY)

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (bm != null) return
        bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    }

    override fun draw(canvas: Canvas?) {
        val data = LinkedList<Vector>()

        repeat(100) {
            data.add(
                Vector(
                    2,
                    Random.nextDouble(width.toDouble()),
                    Random.nextDouble(height.toDouble())
                )
            )
        }



        super.draw(canvas)
        val p = Paint()

        val clusters = KMeansAlgorithm.doAlgo(2, data, 2)

        clusters.forEachIndexed { i, cc ->
            p.color = colorArray[i]
            cc.dataPoints.forEach {
                canvas!!.drawCircle(it.contents[0].toFloat(), it.contents[1].toFloat(), 10F, p)
            }

        }



        invalidate()
    }
}
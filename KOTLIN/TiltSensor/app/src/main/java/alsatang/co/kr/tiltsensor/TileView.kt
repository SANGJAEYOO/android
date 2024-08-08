package alsatang.co.kr.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TileView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX: Float = 0f;
    private var cY: Float = 0F;

    private var xCoord: Float = 0f;
    private var yCoord: Float = 0f;

    fun onSensorEvent(event: SensorEvent) {
        // 화면을 가로 세로 돌렸으면 X Y 축을 바꾼다.
        yCoord = event.values[0] * 20;
        xCoord = event.values[1] * 20;

        invalidate();    //onDraw 를 다시 호출한다.

    }

    init {
        // 녹색 페안트
        greenPaint.color = Color.GREEN
        // 검은섹 ㅌㅔ두리 페인드
        blackPaint.style = Paint.Style.STROKE

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //super.onSizeChanged(w, h, oldw, oldh)
        cX = w / 2f
        cY = h / 2f
    }


    override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas)
        canvas?.drawCircle(cX, cY, 100f, blackPaint);
        canvas?.drawCircle(xCoord + cX,  yCoord +  cY, 100f, greenPaint);

        canvas?.drawLine(cX-20, cY, cX+20, cY, blackPaint);
        canvas?.drawLine(cX, cY-20, cX, cY+20, blackPaint);
    }

}
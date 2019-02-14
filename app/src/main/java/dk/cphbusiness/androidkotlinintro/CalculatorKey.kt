package dk.cphbusiness.androidkotlinintro

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.dip

class CalculatorKey(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val text: String
    val category: Int
    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CalculatorKey,
                0, 0
                ).apply {
            try {
                text = getString(R.styleable.CalculatorKey_text)
                category = getInteger(R.styleable.CalculatorKey_category, 0)
                }
            finally { recycle() }
            }
        }
    private val textPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = dip(20f).toFloat()
        }

    private val backPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.rgb(0.5f, 0.5f, 0.5f)
        style = Paint.Style.FILL
        }

    private val shadowPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.rgb(0.2f, 0.2f, 0.2f)
        }
    private val lightPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.rgb(0.7f, 0.7f, 0.7f)
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(dip(if (category == 9) 100 else 40), dip(40))
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        val d = h/8
        val fm = textPaint.fontMetrics
        val th = fm.descent - fm.ascent
        val ty = (h - th)/2 - fm.ascent
        val tw = textPaint.measureText(text)
        val tx = (w - tw)/2
        val shadowPath = Path().apply {
            moveTo(0f, h)
            lineTo(d, h - d)
            lineTo(w - d, h - d)
            lineTo(w - d, d)
            lineTo(w, 0f)
            lineTo(w, h)
            lineTo(0f, h)
            }
        val lightPath = Path().apply {
            moveTo(0f, h)
            lineTo(d, h - d)
            lineTo(d, d)
            lineTo(w - d, d)
            lineTo(w, 0f)
            lineTo(0f, 0f)
            lineTo(0f, h)
            }
        canvas.apply {
            drawRect(0f, 0f, w, h, backPaint)
            drawPath(lightPath, lightPaint)
            drawPath(shadowPath, shadowPaint)
            drawText(text, tx, ty, textPaint)
            }
        }
    }
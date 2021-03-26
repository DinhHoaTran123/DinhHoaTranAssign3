package project.assignment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;

public class PaintLine extends View implements OnTouchListener {

    private Canvas m_Canvas;

    private Path m_Path;

    private android.graphics.Paint m_Paint;

    private ArrayList<Pair<Path, android.graphics.Paint>> paths = new ArrayList<Pair<Path, android.graphics.Paint>>();

    private ArrayList<Pair<Path, android.graphics.Paint>> undonePaths = new ArrayList<Pair<Path, android.graphics.Paint>>();

    private float mX, mY;

    private static final float TOUCH_TOLERANCE = 4;

    private boolean isEraserActive = false;

    public PaintLine(Context context, AttributeSet attr) {
        super(context, attr);

        setFocusable(true);

        setFocusableInTouchMode(true);

        setBackgroundColor(Color.WHITE);

        this.setOnTouchListener(this);

        onCanvasInitialization();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        Log.d("strokeColor", String.valueOf(strokeColor));
        m_Paint.setColor(strokeColor);
    }

    private int strokeColor ;

    public void setStrokeWith(int strokeWith) {
        this.strokeWith = strokeWith;
        m_Paint.setStrokeWidth(strokeWith);
    }

    private int strokeWith;


    public void onCanvasInitialization() {

        m_Paint = new android.graphics.Paint();
        m_Paint.setAntiAlias(true);
        m_Paint.setDither(true);
        m_Paint.setColor(this.strokeColor);
        m_Paint.setStyle(android.graphics.Paint.Style.STROKE);
        m_Paint.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        m_Paint.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        m_Paint.setStrokeWidth(this.strokeWith);

        m_Canvas = new Canvas();


        m_Path = new Path();
        android.graphics.Paint newPaint = new android.graphics.Paint(m_Paint);
        paths.add(new Pair<Path, android.graphics.Paint>(m_Path, newPaint));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Pair<Path, android.graphics.Paint> p : paths) {
            canvas.drawPath(p.first, p.second);
        }
    }

    private void touch_start(float x, float y) {

        if (isEraserActive) {
            m_Paint.setColor(this.strokeColor);
            m_Paint.setStrokeWidth(this.strokeWith);
            android.graphics.Paint newPaint = new android.graphics.Paint(m_Paint); // Clones the mPaint object
            paths.add(new Pair<Path, android.graphics.Paint>(m_Path, newPaint));

        } else {
            m_Paint.setColor(this.strokeColor);
            m_Paint.setStrokeWidth(this.strokeWith);
            android.graphics.Paint newPaint = new android.graphics.Paint(m_Paint); // Clones the mPaint object
            paths.add(new Pair<Path, android.graphics.Paint>(m_Path, newPaint));

        }


        m_Path.reset();
        m_Path.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            m_Path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        m_Path.lineTo(mX, mY);

        // commit the path to our offscreen
        m_Canvas.drawPath(m_Path, m_Paint);

        // kill this so we don't double draw
        m_Path = new Path();
        // Clones the mPaint object
        android.graphics.Paint newPaint = new android.graphics.Paint(m_Paint);
        paths.add(new Pair<Path, android.graphics.Paint>(m_Path, newPaint));
    }

    public void reset()
    {
        paths.clear();
        invalidate();
    }

}

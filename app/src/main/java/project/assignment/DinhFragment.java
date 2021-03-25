package project.assignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DinhFragment extends Fragment {
    private TextView tvFullName ;
    RelativeLayout relativeLayout;
    Paint paint;
    View view;
    Path path2;
    Bitmap bitmap;
    Canvas canvas;
    Button buttonClear, buttonGreen, buttonBlue, buttonPink;
    private int checkColor = 0 ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dinh, container, false);
        initView(rootView);
        return rootView;
    }
    private void initView(View view){
        tvFullName = view.findViewById(R.id.usernameHome);
        relativeLayout = view.findViewById(R.id.rlPaint);
        buttonClear = view.findViewById(R.id.buttonClear);
        buttonGreen = view.findViewById(R.id.buttonGreen);
        buttonBlue = view.findViewById(R.id.buttonBlue);
        buttonPink = view.findViewById(R.id.buttonPink);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvFullName.setText(requireActivity().getString(R.string.firt_name) + requireActivity().getString(R.string.student_id));
        drawerLine(requireActivity().getColor(R.color.black), 1);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path2.reset();
                view.invalidate();
                Log.d("CheckColor", String.valueOf(checkColor));
                switch (checkColor){
                    case  0 : {
                        drawerLine(requireActivity().getColor(R.color.black), 5);
                        break;
                    }
                    case  1 : {
                        drawerLine(requireActivity().getColor(R.color.green), 10);
                        break;
                    }
                    case  2 : {
                        drawerLine(requireActivity().getColor(R.color.blue), 15);
                        break;
                    }
                    case  3 : {
                        drawerLine(requireActivity().getColor(R.color.pink), 20);
                        break;
                    }
                }

            }
        });

        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path2.reset();
                view.invalidate();
                drawerLine(requireActivity().getColor(R.color.green), 10);
                checkColor = 1;
            }
        });

        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path2.reset();
                view.invalidate();
                drawerLine(requireActivity().getColor(R.color.blue), 15);
                checkColor = 2;
            }
        });

        buttonPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path2.reset();
                view.invalidate();
                drawerLine(requireActivity().getColor(R.color.pink), 20);
                checkColor = 3 ;
            }
        });

    }


    private void drawerLine(int color , int stroke){
        view = new SketchSheetView(requireContext());

        paint = new Paint();

        path2 = new Path();

        relativeLayout.addView(view, new ViewGroup.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        paint.setDither(true);

        paint.setColor(color);

        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeJoin(Paint.Join.ROUND);

        paint.setStrokeCap(Paint.Cap.ROUND);

        paint.setStrokeWidth(stroke);
    }

    class SketchSheetView extends View {

        public SketchSheetView(Context context) {

            super(context);

            bitmap = Bitmap.createBitmap(820, 480, Bitmap.Config.ARGB_4444);

            canvas = new Canvas(bitmap);

            this.setBackgroundColor(Color.WHITE);
        }

        private ArrayList<DrawingClass> DrawingClassArrayList = new ArrayList<DrawingClass>();

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            DrawingClass pathWithPaint = new DrawingClass();

            canvas.drawPath(path2, paint);

            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                path2.moveTo(event.getX(), event.getY());

                path2.lineTo(event.getX(), event.getY());
            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                path2.lineTo(event.getX(), event.getY());

                pathWithPaint.setPath(path2);

                pathWithPaint.setPaint(paint);

                DrawingClassArrayList.add(pathWithPaint);
            }

            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (DrawingClassArrayList.size() > 0) {
                canvas.drawPath(
                        DrawingClassArrayList.get(DrawingClassArrayList.size() - 1).getPath(),
                        DrawingClassArrayList.get(DrawingClassArrayList.size() - 1).getPaint());
            }
        }
    }

    public class DrawingClass {

        Path DrawingClassPath;
        Paint DrawingClassPaint;

        public Path getPath() {
            return DrawingClassPath;
        }

        public void setPath(Path path) {
            this.DrawingClassPath = path;
        }


        public Paint getPaint() {
            return DrawingClassPaint;
        }

        public void setPaint(Paint paint) {
            this.DrawingClassPaint = paint;
        }
    }
}

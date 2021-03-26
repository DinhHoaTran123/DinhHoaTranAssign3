package project.assignment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DinhFragment extends Fragment {
    private TextView tvFullName ;
    PaintLine drawing;
    Button buttonClear, buttonGreen, buttonBlue, buttonPink;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dinh, container, false);
        initView(rootView);
        return rootView;
    }
    private void initView(View view){
        tvFullName = view.findViewById(R.id.usernameHome);
        buttonClear = view.findViewById(R.id.buttonClear);
        buttonGreen = view.findViewById(R.id.buttonGreen);
        buttonBlue = view.findViewById(R.id.buttonBlue);
        buttonPink = view.findViewById(R.id.buttonPink);
        drawing = view.findViewById(R.id.rlPaint);
        drawing.setStrokeColor(requireActivity().getColor(R.color.black));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvFullName.setText(requireActivity().getString(R.string.firt_name) + requireActivity().getString(R.string.student_id));
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawing.reset();
            }
        });

        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawing.setStrokeColor(requireActivity().getColor(R.color.green));
                drawing.setStrokeWith(5);
            }
        });

        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawing.setStrokeColor(requireActivity().getColor(R.color.blue));
                drawing.setStrokeWith(20);
            }
        });

        buttonPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawing.setStrokeColor(requireActivity().getColor(R.color.pink));
                drawing.setStrokeWith(40);
            }
        });

    }
}

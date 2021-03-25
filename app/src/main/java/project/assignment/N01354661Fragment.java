package project.assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class N01354661Fragment extends Fragment {
    private ImageView imageView;
    private Button btnStart, btnStop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_n01254661, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {
        imageView = view.findViewById(R.id.imageEarth);
        btnStart = view.findViewById(R.id.startAnimation);
        btnStop = view.findViewById(R.id.stopAnimation);


    }

    private void startRotateAnimation(Boolean check) {
        if (check) {
            Animation rotate = AnimationUtils.loadAnimation(requireActivity(), R.anim.rotate_animation);
            imageView.startAnimation(rotate);
        } else {
            imageView.setAnimation(null);
        }
    }

    private void startScaleAnimation(Boolean check) {
        if (check) {
            Animation scale = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_animation);
            imageView.startAnimation(scale);
        } else {
            imageView.setAnimation(null);
        }
    }

    private void startTranslateAnimation(Boolean check) {
        if (check) {
            Animation translate = AnimationUtils.loadAnimation(requireActivity(), R.anim.translate_animation);
            imageView.startAnimation(translate);
        } else {
            imageView.setAnimation(null);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnStart.setOnClickListener(view1 -> startTranslateAnimation(true));

        btnStop.setOnClickListener(view12 -> startTranslateAnimation(false));

    }
}

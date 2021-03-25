package project.assignment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


public class HoaFragment extends Fragment {
    private ImageView spriteImage ;
    private int[] filesNames = new int[]{R.drawable.frame_1 , R.drawable.frame_2 , R.drawable.frame_3 , R.drawable.frame_4 , R.drawable.frame_5 , R.drawable.frame_6};
    private CustomDurationAnimationDrawable animDrawable ;
    private Drawable drawable1, drawable2, drawable3, drawable4, drawable5, drawable6;
    private Button startDuration200 , startDuration400 ,startDuration600 ,startDuration800 ,startPermission;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hoa, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        spriteImage = view.findViewById(R.id.sprite_image);
        startDuration200 = view.findViewById(R.id.btnStartDuration200);
        startDuration400 = view.findViewById(R.id.btnStartDuration400);
        startDuration600 = view.findViewById(R.id.btnStartDuration600);
        startDuration800 = view.findViewById(R.id.btnStartDuration800);
        startPermission = view.findViewById(R.id.startPermission);
        startDuration(50);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startDuration200.setOnClickListener(view1 -> { startDuration(200); });
        startDuration400.setOnClickListener(view1 -> { startDuration(400); });
        startDuration600.setOnClickListener(view1 -> { startDuration(600); });
        startDuration800.setOnClickListener(view1 -> { startDuration(800); });
        startPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
        });
    }
    private void startDuration(int duration){
        animDrawable = new CustomDurationAnimationDrawable();
        animDrawable.stop();
        drawable1 = requireActivity().getDrawable(R.drawable.frame_1);
        drawable2 = requireActivity().getDrawable(R.drawable.frame_2);
        drawable3 = requireActivity().getDrawable(R.drawable.frame_3);
        drawable4 = requireActivity().getDrawable(R.drawable.frame_4);
        drawable5 = requireActivity().getDrawable(R.drawable.frame_5);
        drawable6 = requireActivity().getDrawable(R.drawable.frame_6);
        animDrawable.addFrame(drawable1, 1000);
        animDrawable.addFrame(drawable2, 1000);
        animDrawable.addFrame(drawable3, 1000);
        animDrawable.addFrame(drawable4, 1000);
        animDrawable.addFrame(drawable5, 1000);
        animDrawable.addFrame(drawable6, 1000);
        spriteImage.setBackgroundDrawable(animDrawable);
        animDrawable.setOneShot(false);
        animDrawable.setDuration(duration);
        animDrawable.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Snackbar.make(startPermission,requireActivity().getString(R.string.denied),Snackbar.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}

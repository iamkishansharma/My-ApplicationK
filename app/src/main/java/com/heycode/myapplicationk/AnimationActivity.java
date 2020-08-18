package com.heycode.myapplicationk;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    AnimationDrawable mAnimationDrawable, ad1;
//    TextView anim_text;
    ImageView anim_image;
Button blink, rotate, fade_in, fade_out, zoom_in, zoom_out, move_right, move_left, bounce, try_anim,all_anim_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mLinearLayout = findViewById(R.id.linear_home);
        mAnimationDrawable = (AnimationDrawable) mLinearLayout.getBackground();
        mAnimationDrawable.setEnterFadeDuration(4500);
        mAnimationDrawable.setExitFadeDuration(4500);
        mAnimationDrawable.start();



//        anim_text = findViewById(R.id.anim_text);
        anim_image = findViewById(R.id.anim_image);
        ad1 = (AnimationDrawable) anim_image.getBackground();
        ad1.setEnterFadeDuration(4500);
        ad1.setExitFadeDuration(4500);


        blink = findViewById(R.id.btn_blink);
        blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.blink);
                anim_image.startAnimation(animation);
            }
        });

        zoom_in = findViewById(R.id.btn_zoomIn);
        zoom_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.zoom_in);
                anim_image.startAnimation(animation);
            }
        });
        zoom_out = findViewById(R.id.btn_zoomOut);
        zoom_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.zoom_out);
                anim_image.startAnimation(animation);
            }
        });

        rotate = findViewById(R.id.btn_rotate);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.rotate);
                anim_image.startAnimation(animation);
            }
        });
        fade_in = findViewById(R.id.btn_fadeIn);
        fade_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.fade_in);
                anim_image.startAnimation(animation);
            }
        });
        fade_out = findViewById(R.id.btn_fadeOut);
        fade_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.fade_out);
                anim_image.startAnimation(animation);
            }
        });

        move_right = findViewById(R.id.btn_move_right);
        move_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.move_right);
                anim_image.startAnimation(animation);
            }
        });

        move_left = findViewById(R.id.btn_move_left);
        move_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.move_left);
                anim_image.startAnimation(animation);
            }
        });
        bounce = findViewById(R.id.btn_bounce);
        bounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.bounce);
                anim_image.startAnimation(animation);
            }
        });
        try_anim = findViewById(R.id.btn_try_anim);
        try_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.try_anim);
                anim_image.startAnimation(animation);
            }
        });
        all_anim_image = findViewById(R.id.btn_all_anim_image);
        all_anim_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ad1.start();
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.try_anim);
                anim_image.startAnimation(animation);
            }
        });


    }
}
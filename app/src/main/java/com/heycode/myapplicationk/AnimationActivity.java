package com.heycode.myapplicationk;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    AnimationDrawable mAnimationDrawable;
    TextView anim_text;
Button blink, rotate, fade_in, fade_out, zoom_in, zoom_out, move_right, move_left, bounce, try_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mLinearLayout = findViewById(R.id.linear_home);
        mAnimationDrawable = (AnimationDrawable) mLinearLayout.getBackground();
        mAnimationDrawable.setEnterFadeDuration(4500);
        mAnimationDrawable.setExitFadeDuration(4500);
        mAnimationDrawable.start();

        anim_text = findViewById(R.id.anim_text);

        blink = findViewById(R.id.btn_blink);
        blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.blink);
                anim_text.startAnimation(animation);
            }
        });

        zoom_in = findViewById(R.id.btn_zoomIn);
        zoom_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.zoom_in);
                anim_text.startAnimation(animation);
            }
        });
        zoom_out = findViewById(R.id.btn_zoomOut);
        zoom_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.zoom_out);
                anim_text.startAnimation(animation);
            }
        });

        rotate = findViewById(R.id.btn_rotate);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.rotate);
                anim_text.startAnimation(animation);
            }
        });
        fade_in = findViewById(R.id.btn_fadeIn);
        fade_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.fade_in);
                anim_text.startAnimation(animation);
            }
        });
        fade_out = findViewById(R.id.btn_fadeOut);
        fade_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.fade_out);
                anim_text.startAnimation(animation);
            }
        });

        move_right = findViewById(R.id.btn_move_right);
        move_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.move_right);
                anim_text.startAnimation(animation);
            }
        });

        move_left = findViewById(R.id.btn_move_left);
        move_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.move_left);
                anim_text.startAnimation(animation);
            }
        });
        bounce = findViewById(R.id.btn_bounce);
        bounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.bounce);
                anim_text.startAnimation(animation);
            }
        });
        try_anim = findViewById(R.id.btn_try_anim);
        try_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.try_anim);
                anim_text.startAnimation(animation);
            }
        });


    }
}
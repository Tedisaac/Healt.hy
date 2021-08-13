package com.example.healthy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context ctx;
    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen,container,false);

        final LottieAnimationView login_slider = view.findViewById(R.id.login_anim);

        ImageView ind1 = view.findViewById(R.id.ind1);
        ImageView ind2 = view.findViewById(R.id.ind2);
        ImageView ind3 = view.findViewById(R.id.ind13);

        TextView slider_login = view.findViewById(R.id.login_slider);
        TextView login_slider_text = view.findViewById(R.id.login_slider_text);

        ImageView next_slide = view.findViewById(R.id.slider_next);
        ImageView previous_slide = view.findViewById(R.id.slider_previous);

        Button get_started = view.findViewById(R.id.get_started);
        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,GetStartedActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
        next_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideActivity.viewPager.setCurrentItem(position+1);

            }
        });
        previous_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideActivity.viewPager.setCurrentItem(position-1);
            }
        });

        switch (position)
        {
            case 0:
                login_slider.setAnimation(R.raw.logg);
                login_slider.playAnimation();
                login_slider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login_slider.playAnimation();
                    }
                });

                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                slider_login.setText("Login");
                login_slider_text.setText("Easily Login");
                previous_slide.setVisibility(View.GONE);
                next_slide.setVisibility(View.VISIBLE);
                break;
            case 1:
                login_slider.setAnimation(R.raw.femaledocc);
                login_slider.playAnimation();
                login_slider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login_slider.playAnimation();
                    }
                });

                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                slider_login.setText("Meet A Doctor");
                login_slider_text.setText("Get Connected to a doctor");
                previous_slide.setVisibility(View.VISIBLE);
                next_slide.setVisibility(View.VISIBLE);
                break;
            case 2:
                login_slider.setAnimation(R.raw.diagnosis);
                login_slider.playAnimation();
                login_slider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login_slider.playAnimation();
                    }
                });

                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                slider_login.setText("Get Diagnosis");
                login_slider_text.setText("Get sufficient diagnosis");
                previous_slide.setVisibility(View.VISIBLE);
                next_slide.setVisibility(View.GONE);
                break;


        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

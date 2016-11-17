package com.example.gavin.spannablestringbuildertest;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mode1();
        mode2();
        mode3();
        mode4();
        mode5();
        mode6();
        mode7();
        mode8();
        mode9();
        //mode10();
    }

    /**
     * 使用SpannableString设置样式——字体颜色
     */
    private void mode1() {
        SpannableString spannableString = new SpannableString("暗影IV已经开始暴走了");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode1)).setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——字体颜色
     */
    private void mode2() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV");
        spannableString.append("已经开始暴走了");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(colorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode2)).setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——背景颜色
     */
    private void mode3() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(bgColorSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode3)).setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——字体大小
     */
    private void mode4() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(20);
        spannableString.setSpan(absoluteSizeSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode4)).setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——粗体\斜体
     */
    private void mode5() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        //setSpan可多次使用
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);//粗体
        spannableString.setSpan(styleSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        StyleSpan styleSpan2 = new StyleSpan(Typeface.ITALIC);//斜体
        spannableString.setSpan(styleSpan2, 3, 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        StyleSpan styleSpan3 = new StyleSpan(Typeface.BOLD_ITALIC);//粗斜体
        spannableString.setSpan(styleSpan3, 6, 9, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode5)).setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——删除线
     */
    private void mode6() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode6)).setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——下划线
     */
    private void mode7() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode7)).setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置样式——图片
     */
    private void mode8() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher);
        //也可以这样
        //Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        //drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //ImageSpan imageSpan1 = new ImageSpan(drawable);
        //将index为6、7的字符用图片替代
        spannableString.setSpan(imageSpan, 6, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView)findViewById(R.id.mode8)).setText(spannableString);
    }

    /**
     * 使用SpannableStringBuilder设置点击事件
     */
    private void mode9() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "请不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        TextView textView = (TextView)findViewById(R.id.mode9);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 使用SpannableStringBuilder事件组合使用
     */
    private void mode10() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("暗影IV已经开始暴走了");
        //图片
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher);
        spannableString.setSpan(imageSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "请不要点我", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(clickableSpan, 2, 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //文字颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
        spannableString.setSpan(colorSpan,5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //文字背景颜色
        BackgroundColorSpan bgColorSpan = new BackgroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(bgColorSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        TextView textView = (TextView)findViewById(R.id.mode10);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

}

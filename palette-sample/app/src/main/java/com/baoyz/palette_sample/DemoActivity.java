package com.baoyz.palette_sample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class DemoActivity extends Activity {

    private ImageView mImageView;
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private TextView mTextView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mImageView = (ImageView) findViewById(R.id.iv_pic);

        mTextView1 = (TextView) findViewById(R.id.tv_1);
        mTextView2 = (TextView) findViewById(R.id.tv_2);
        mTextView3 = (TextView) findViewById(R.id.tv_3);
        mTextView4 = (TextView) findViewById(R.id.tv_4);
        mTextView5 = (TextView) findViewById(R.id.tv_5);
        mTextView6 = (TextView) findViewById(R.id.tv_6);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic_1);

        // 从bitmap中提取颜色
        extract(bitmap);
    }

    private void extract(Bitmap bitmap) {

        // 图片预览
        mImageView.setImageBitmap(bitmap);

        // 提取颜色
        // Palette palette = Palette.generate(bitmap);   // 此方法可能会阻塞主线程，建议使用异步方法
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // 提取完毕
                Palette.Swatch vibrant = palette.getVibrantSwatch();    // 有活力的颜色
                Palette.Swatch darkVibrant = palette.getDarkVibrantSwatch();    // 有活力的暗色
                Palette.Swatch lightVibrant = palette.getLightVibrantSwatch();    // 有活力的亮色
                Palette.Swatch muted = palette.getMutedSwatch();    // 柔和的颜色
                Palette.Swatch darkMuted = palette.getDarkMutedSwatch();    // 柔和的暗色
                Palette.Swatch lightMuted = palette.getLightMutedSwatch();    // 柔和的亮色

                // 修改Actionbar背景颜色
                getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));

                mTextView1.setText("有活力的颜色");
                if (vibrant != null) {
                    mTextView1.setBackgroundColor(vibrant.getRgb());
                    mTextView1.setTextColor(vibrant.getTitleTextColor());
                }

                mTextView2.setText("有活力的暗色");
                if (darkVibrant != null) {
                    mTextView2.setBackgroundColor(darkVibrant.getRgb());
                    mTextView2.setTextColor(darkVibrant.getTitleTextColor());
                }

                mTextView3.setText("有活力的亮色");
                if (lightVibrant != null) {
                    mTextView3.setBackgroundColor(lightVibrant.getRgb());
                    mTextView3.setTextColor(lightVibrant.getTitleTextColor());
                }

                mTextView4.setText("柔和的颜色");
                if (muted != null) {
                    mTextView4.setBackgroundColor(muted.getRgb());
                    mTextView4.setTextColor(muted.getTitleTextColor());
                }

                mTextView5.setText("柔和的暗色");
                if (darkMuted != null) {
                    mTextView5.setBackgroundColor(darkMuted.getRgb());
                    mTextView5.setTextColor(darkMuted.getTitleTextColor());
                }

                mTextView6.setText("柔和的亮色");
                if (lightMuted != null) {
                    mTextView6.setBackgroundColor(lightMuted.getRgb());
                    mTextView6.setTextColor(lightMuted.getTitleTextColor());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_pic_1:
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.pic_1);
                // 从bitmap中提取颜色
                extract(bitmap1);
                break;
            case R.id.action_pic_2:
                Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.pic_2);
                // 从bitmap中提取颜色
                extract(bitmap2);
                break;
            case R.id.action_pic_3:
                Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.pic_3);
                // 从bitmap中提取颜色
                extract(bitmap3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

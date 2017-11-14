package compindia.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rd.PageIndicatorView;
import com.rd.draw.data.Indicator;

import static android.support.v4.view.ViewPager.*;

/**
 * Created by compindi on 30-10-2017.
 */

public class MainActivity extends AppCompatActivity implements OnPageChangeListener, OnClickListener {
    int[] buttontxt = {R.string.get_started_txt, R.string.hello_txt, R.string.get_started_txt, R.string.get_started_txt, R.string.grant_txt};
    int[] buttontxtColor = {R.color.btn_color_blue, R.color.btn_color_pink, R.color.btn_color_dark_blue, R.color.btn_color_shadow, R.color.btn_color_yellow};
    int[] buttonBackgrounds = {R.drawable.rounded_button1, R.drawable.rounded_button2, R.drawable.rounded_button3, R.drawable.rounded_button4, R.drawable.rounded_button5};
    int[] background_color = {R.color.blue, R.color.pink, R.color.dark_blue, R.color.shadow, R.color.yellow
    };
    Button button;
    ImageView imgBack, imgBackBlack;
    ImageView imgForward, imgForwardBlack;
    ViewPager viewPager;
    MyPageAdapter myPageAdapter;
    int currentPosition;
    PageIndicatorView indicator;
    RelativeLayout loutMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPageAdapter = new MyPageAdapter(this);
        button = (Button) findViewById(R.id.button);
        imgBack = (ImageView) findViewById(R.id.back_button);
        imgForward = (ImageView) findViewById(R.id.forward_button);
        indicator = (PageIndicatorView) findViewById(R.id.indicator);
        loutMain = (RelativeLayout) findViewById(R.id.lout_main);
        loutMain.setBackgroundColor(getResources().getColor(R.color.blue));
        getWindow().setStatusBarColor(getResources().getColor(R.color.btn_color_blue));
        imgForward.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgBack.setVisibility(INVISIBLE);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setOnPageChangeListener(MainActivity.this);
        indicator.setViewPager(viewPager);
        viewPager.setPageTransformer(true, new PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                view.setPivotX(position > 0 ? 0 : view.getWidth());
                view.setPivotY(0);
//                view.setRotationY(-90f * position);
//                view.setPivotX(position < 0f ? view.getWidth() : 0f);
//                view.setPivotY(view.getHeight() * 0.5f);
//                view.setRotationY(90f * position);
//                view.setPivotX(position < 0 ? 0 : view.getWidth());
//                view.setScaleX(position < 0 ? 1f + position : 1f - position);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        imgForward.setVisibility(VISIBLE);
        imgBack.setVisibility(VISIBLE);
        if (position == 3 || position == 4) {
            imgBack.setImageResource(R.drawable.back_button_black);
            imgForward.setImageResource(R.drawable.forward_button_black);
        } else {
            imgBack.setImageResource(R.drawable.back_button);
            imgForward.setImageResource(R.drawable.forward_button);
        }
        if (position == 0) {
            imgBack.setVisibility(INVISIBLE);
        } else if (position == 4) {
            imgForward.setVisibility(INVISIBLE);
        }
        currentPosition = position;
        button.setText(buttontxt[position]);
        button.setTextColor(getResources().getColor(buttontxtColor[position]));
        imgBack.setBackgroundResource(buttonBackgrounds[position]);
        imgForward.setBackgroundResource(buttonBackgrounds[position]);
        loutMain.setBackgroundColor(background_color[position]);
        getWindow().setStatusBarColor(getResources().getColor(buttontxtColor[position]));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                --currentPosition;
                viewPager.setCurrentItem(currentPosition);
                break;
            case R.id.forward_button:
                ++currentPosition;
                viewPager.setCurrentItem(currentPosition);
                break;


        }
    }
}

class MyPageAdapter extends PagerAdapter {
    int[] background_color = {R.color.blue, R.color.pink, R.color.dark_blue, R.color.shadow, R.color.yellow
    };
    int[] images = {R.drawable.art_material_metaphor, R.drawable.art_material_bold, R.drawable.art_material_motion, R.drawable.art_material_shadow};
    int[] strings = {R.string.material_txt, R.string.bold_graphic_heading, R.string.motion_heading, R.string.light_heading, R.string.requist_heading};
    int[] substrings = {R.string.material_sub_txt, R.string.bold_sub_txt, R.string.motion_sub_txt,
            R.string.light_sub_txt,
            R.string.request_sub_txt,};
    Context mcontext;
    LayoutInflater inflater;

    public MyPageAdapter(Context context) {
        mcontext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemview = inflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) itemview.findViewById(R.id.image);
        TextView txtHeading = (TextView) itemview.findViewById(R.id.txt_heading);
        TextView txtSubtext = (TextView) itemview.findViewById(R.id.txt_sub_heading);
        LinearLayout loutItem = (LinearLayout) itemview.findViewById(R.id.lout_items);
        if (position <= 3) {
            imageView.setImageResource(images[position]);
        } else {
            imageView.setVisibility(View.GONE);
            loutItem.setGravity(Gravity.CENTER);
        }
        if (position == 0) {
            txtSubtext.setTextColor(0xFFACE3FB);
        }
        if (position == 1) {
            txtSubtext.setTextColor(0xFFFDAEC9);
        }
        if (position == 2) {
            txtSubtext.setTextColor(0xFFCFAEE4);
        }
        if (position >= 3) {
            txtHeading.setTextColor(mcontext.getResources().getColor(R.color.black));
            txtSubtext.setTextColor(mcontext.getResources().getColor(R.color.sub_txt_color));
        }

        loutItem.setBackgroundColor(mcontext.getResources().getColor(background_color[position]));
        txtHeading.setText(strings[position]);
        txtSubtext.setText(substrings[position]);
        container.addView(itemview);
        return itemview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

package com.lanou3g.mostbeautifulproperty.mine.uiview.setting.aboutus;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;

/**
 *
 */

public class AboutUsActivity extends BaseActivity{

    private TextView mTvBody;
    private TextView mTvTitle;
    private ImageView mIvReturn;

    @Override
    protected int setLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        mTvBody = bindView(R.id.tv_about_us_body);
        mTvTitle = bindView(R.id.tv_include_setting_title);
        mIvReturn = bindView(R.id.btn_include_setting_return);
    }

    @Override
    protected void initData() {
        mTvTitle.setText("关于我们");
        mTvBody.setText("「最美有物」全球原创设计师产品发现和评价平台——不从众的好设计\n" +
                "\n" +
                "纽约、巴黎、米兰、伦敦、东京、蒙特利尔...最美团队拜访每一位设计师，了解他们的创作理念和背后的故事，挑选他们最好的作品，皮包、钱包、首饰，从手机壳到各种各样让生活变的更美的产品。\n" +
                "\n" +
                "用心、原创、高颜值又实用，每一件好产品背后都有故事，都有独特的设计理念。我们在乎的不仅仅是产品，更是每一位优秀的设计师、美好事物的创造者。现在开始，设计师们终于有了展示自己作品的舞台。这些真正有内涵的产品，值得送给自己，值得送给对的人。\n" +
                "\n" +
                "你和我对设计师们成果的态度，决定了哪一件产品才是真正的时尚与流行。最美团队并不试图推销东西，我们希望大家一起找到那些真正好的产品。点击喜欢或者不喜欢，表达出自己的态度，也看看其他人的态度，我们就可以一起定义时尚。\n" +
                "\n" +
                "小众还是大众？\n" +
                "你不在乎，我也不在乎\n" +
                "我们只是不想从众\n" +
                "\n" +
                "满大街都是的品牌\n" +
                "也不赖\n" +
                "我们只是不喜欢\n" +
                "\n" +
                "在乎颜值\n" +
                "不讲究不凑合\n" +
                "不一样的好品位\n" +
                "\n" +
                "我们一起\n" +
                "在「最美有物」");

        mIvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

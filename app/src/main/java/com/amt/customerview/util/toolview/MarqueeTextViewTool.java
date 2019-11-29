package com.amt.customerview.util.toolview;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amt.customerview.customerview.R;
import com.amt.customerview.image.ViewbgLoader;
import com.amt.customerview.util.Constant;
import com.amt.customerview.util.commonbean.CommonBean;
import com.amt.customerview.util.customizeview.MarqueeTextView;


/**
 * Created by Administrator on 2019/5/17.
 */
public class MarqueeTextViewTool {

    public void creatView(MarqueeTextViewToolBean marqueeTextViewToolBean ,CommonBean commonBean){
        RelativeLayout rootlayout = new RelativeLayout(commonBean.getContext());
        rootlayout.setOnFocusChangeListener(commonBean.getFocusChangeListener());
        rootlayout.setFocusable(marqueeTextViewToolBean.focus);
        rootlayout.requestFocus();
        if (marqueeTextViewToolBean.focus) {
            rootlayout.setBackgroundResource(R.drawable.bgseletor);
        }

        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if (marqueeTextViewToolBean.focus) {
            //有背景框的话 就减去背景框的长度
            params.setMargins(marqueeTextViewToolBean.getMarleft()- Constant.margin,marqueeTextViewToolBean.getMartop()-Constant.margin,0,0);
        }else{
            params.setMargins(marqueeTextViewToolBean.getMarleft() ,marqueeTextViewToolBean.getMartop() ,0,0);
        }

        rootlayout.setLayoutParams(params);
        rootlayout.setTag(commonBean.getTag());
        rootlayout.setOnClickListener(commonBean.getOnClickListener());

        RelativeLayout.LayoutParams imgparams =  new RelativeLayout.LayoutParams(marqueeTextViewToolBean.getWidth(),marqueeTextViewToolBean.getHeigh());
        imgparams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);//居中显示
        MarqueeTextView textView = new MarqueeTextView(commonBean.getContext());

        String text = marqueeTextViewToolBean.getText();
        text=text+"    "+text+
                text+"    "+text+
                text+"    "+text+
                text+"    "+text+
                text+"    "+text+
                text+"    "+text+
                text+"    "+text+
                text+"    "+text+

                text+"    "+text;

        textView.setWidth(marqueeTextViewToolBean.getWidth());
        textView.setHeight(marqueeTextViewToolBean.getHeigh());
        textView.setText(text);
        textView.setTextSize(marqueeTextViewToolBean.getTextsize());
        textView.setTextColor(commonBean.getContext().getResources().getColor(R.color.whithe));

        textView.setGravity(Gravity.CENTER);


        if (marqueeTextViewToolBean.getTextviewbgurl()!=null&&marqueeTextViewToolBean.getTextviewbgurl().contains("http")){
            ViewbgLoader.getInstance().setBg(commonBean.getContext(),marqueeTextViewToolBean.getTextviewbgurl(),textView);
        }else {
            textView.setBackgroundColor(android.graphics.Color.parseColor("#242f40"));
        }
        if (marqueeTextViewToolBean.getTextcolor()!=null){
            textView.setTextColor(android.graphics.Color.parseColor(marqueeTextViewToolBean.getTextcolor()));
        }
        rootlayout.addView(textView);
        commonBean.getLayout().addView(rootlayout);

    }

}

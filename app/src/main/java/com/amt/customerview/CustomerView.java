package com.amt.customerview;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.amt.customerview.customerview.R;
import com.amt.customerview.image.ViewbgLoader;
import com.amt.customerview.util.Constant;
import com.amt.customerview.util.commonbean.CommonBean;
import com.amt.customerview.util.toolview.BannerViewTool;
import com.amt.customerview.util.toolview.ImageViewTool;
import com.amt.customerview.util.toolview.ImageViewToolBean;
import com.amt.customerview.util.toolview.ListViewTool;
import com.amt.customerview.util.toolview.ListViewToolBean;
import com.amt.customerview.util.toolview.MarqueeTextViewTool;
import com.amt.customerview.util.toolview.MarqueeTextViewToolBean;
import com.amt.customerview.util.toolview.TextClockViewTool;
import com.amt.customerview.util.toolview.TextClockViewToolBean;
import com.amt.customerview.util.toolview.TextViewTool;
import com.amt.customerview.util.toolview.TextViewToolBean;
import com.amt.customerview.util.toolview.VideoViewTool;
import com.amt.customerview.util.toolview.VideoViewToolBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/11/11.
 */
public class CustomerView   {

    RelativeLayout parentlayout;
    BannerViewTool bannerViewTool;
    CommonBean commonBean = new CommonBean();
    ImageViewTool imageViewTool = new ImageViewTool();//图片控件
    MarqueeTextViewTool marqueeTextViewTool = new MarqueeTextViewTool();//跑马灯控件
    TextViewTool textViewTool = new TextViewTool();  //文字控件
    TextClockViewTool textClockViewTool  = new TextClockViewTool ();//时间控件
    ListViewTool listViewTool = new ListViewTool();
    VideoViewToolBean videoViewToolBean = new VideoViewToolBean();
    VideoViewTool videoViewTool = new VideoViewTool();

    public void initView (String data, View.OnFocusChangeListener onFocusChangeListener, View.OnClickListener onClickListener, RelativeLayout parentlayout, Context context) {
        this.parentlayout  =  parentlayout;
        // 公共属性
        commonBean .setContext(context);
        commonBean.setFocusChangeListener(onFocusChangeListener);
        commonBean.setOnClickListener(onClickListener);
        commonBean.setLayout(parentlayout);


        try {
            String result = data;
            JSONObject jSONObject = new JSONObject(result);
            JSONObject jsonData = jSONObject.getJSONObject("data");
            JSONArray jsonArray = jsonData.getJSONArray("components");


            if (!jsonData.getJSONObject("backgroundImage").isNull("diskPath")){
                String bgurl = Constant.pichttp+ jsonData.getJSONObject("backgroundImage").getString("diskPath");
                ViewbgLoader.getInstance().setBg(context, bgurl,parentlayout);
            }else {
                parentlayout.setBackground(context.getResources().getDrawable(R.mipmap.bg));
            }



            for (int a =0 ;a<jsonArray.length();a++){
                String itemcomponents = jsonArray.getJSONObject(a).getString("properties").replaceAll("\\\\","");
                JSONObject itemcJson = new JSONObject(itemcomponents);
                String type =itemcJson.getString("type");
                switch (type){
                    case "s_img":
                        ImageViewToolBean imageViewToolBean_nf = new ImageViewToolBean();
                        //没有焦点图片
                        //  imageViewToolBean.setUrl(itemcJson.getString("url"));
                        if (!itemcJson.getJSONObject("url").isNull("diskPath")){
                            imageViewToolBean_nf.setUrl(itemcJson.getJSONObject("url").getString("diskPath"));
                        }else {
                            imageViewToolBean_nf.setUrl("");
                        }
                        //imageViewToolBean_nf.setUrl("http://192.168.2.201:1001/fileImage/imageShow/24da52d8da3442e5ad2209cee1762380");
                        imageViewToolBean_nf.setHeigh(itemcJson.getInt("height"));
                        imageViewToolBean_nf.setWidth(itemcJson.getInt("width"));
                        imageViewToolBean_nf.setMarleft( itemcJson.getInt("left"));
                        imageViewToolBean_nf.setMartop(itemcJson.getInt("top"));
                        imageViewToolBean_nf.setFocus(false);
                        imageViewTool.creatView(imageViewToolBean_nf,commonBean);
                        commonBean.setTag(imageViewToolBean_nf);
                        break;

                    case "s_text":
                        TextViewToolBean textViewToolBean = new TextViewToolBean();
                        //没有焦点文字
                        textViewToolBean.setFocus(false);
                        textViewToolBean.setHeigh(itemcJson.getInt("lineHeight"));
                        textViewToolBean.setWidth(itemcJson.getInt("width"));
                        textViewToolBean.setMarleft( itemcJson.getInt("left"));
                        textViewToolBean.setMartop(itemcJson.getInt("top"));
                        textViewToolBean.setTextsize(itemcJson.getInt("fontSize"));
                        textViewToolBean.setText (itemcJson.getString("text"));
                        //textViewToolBean.setTextCorol(itemcJson.getString("color")); textAlign
                        textViewTool.creatView(textViewToolBean,commonBean);
                        commonBean.setTag(textViewToolBean);
                        break;
                    case "s_time1":
                        //没有焦点
                        break;
                    case "s_time2":
                        TextClockViewToolBean textClockViewToolBean = new TextClockViewToolBean();
                        textClockViewToolBean.setFocus(false);
                        textClockViewToolBean.setHeigh(itemcJson.getInt("height"));
                        textClockViewToolBean.setWidth(itemcJson.getInt("width"));
                        textClockViewToolBean.setMarleft( itemcJson.getInt("left"));
                        textClockViewToolBean.setMartop(itemcJson.getInt("top"));
                        textClockViewToolBean.setTextsize(itemcJson.getInt("fontSize"));
                        textClockViewToolBean.setFormattype(itemcJson.getString("timeFormat"));
//                                textClockViewToolBean.setTextCorol(itemcJson.getInt("#FFFFFF"));
                        textClockViewTool.creatView(textClockViewToolBean,commonBean);
                        commonBean.setTag("s_time2");
                        //没有焦点

                        break;
                    case "s_vas_img":
                        //有焦点图片
                        ImageViewToolBean imageViewToolBean_f = new ImageViewToolBean();
                        if (!itemcJson.getJSONObject("iconPic").getJSONObject("url").isNull("diskPath")){
                            imageViewToolBean_f.setUrl(itemcJson.getJSONObject("iconPic").getJSONObject("url").getString("diskPath"));
                            System.out.println("----------->pic"+imageViewToolBean_f.getUrl());
                        }else {
                            imageViewToolBean_f.setUrl("");
                        }
                        imageViewToolBean_f.setHeigh(itemcJson.getInt("height"));
                        imageViewToolBean_f.setWidth(itemcJson.getInt("width"));
                        imageViewToolBean_f.setMarleft(itemcJson.getInt("left"));
                        imageViewToolBean_f.setMartop(itemcJson.getInt("top"));
                        imageViewToolBean_f.setFocustype(itemcJson.getInt("focusType"));
                        if (!itemcJson.getJSONObject("focusPic").getJSONObject("url").isNull("diskPath")){
                            imageViewToolBean_f.setFocuspicurl(itemcJson.getJSONObject("focusPic").getJSONObject("url").getString("diskPath"));
                        }else {
                            imageViewToolBean_f.setFocuspicurl("");
                        }

                        imageViewToolBean_f.setFocus(true);
                        commonBean.setTag(imageViewToolBean_f);
                        imageViewTool.creatView(imageViewToolBean_f,commonBean);
                        break;
                    case "s_marquee":
                        MarqueeTextViewToolBean marqueeTextViewToolBean = new MarqueeTextViewToolBean();
                        //跑马灯
                        //没有焦点
                        commonBean.setTag("s_marquee");
                        if (!itemcJson.getJSONObject("backgroundImage").isNull("diskPath")){
                            marqueeTextViewToolBean.setTextviewbgurl(itemcJson.getJSONObject("backgroundImage").getString("diskPath"));
                        }else {
                            marqueeTextViewToolBean.setTextviewbgurl("");
                        }

                        marqueeTextViewToolBean.setFocus(false);
                        marqueeTextViewToolBean.setHeigh(itemcJson.getInt("height"));
                        marqueeTextViewToolBean.setWidth(itemcJson.getInt("width"));
                        marqueeTextViewToolBean.setMarleft( itemcJson.getInt("left"));
                        marqueeTextViewToolBean.setMartop(itemcJson.getInt("top"));
                        marqueeTextViewToolBean.setTextsize(itemcJson.getInt("fontSize"));
                        marqueeTextViewToolBean.setText(itemcJson.getString("text"));
                        marqueeTextViewToolBean.setTextcolor(itemcJson.getString("color"));
                        marqueeTextViewTool.creatView(marqueeTextViewToolBean,commonBean);


                        break;
                    case "d_vas_list":
                        ListViewToolBean listViewToolBean = new ListViewToolBean();
                        List<ListItemBean> listItemBeen = new ArrayList<>();
                        //pagecode name  新闻标题
                        String pageCode = itemcJson.getString("pageCode");
                        commonBean.setTag("d_vas_list");
                        listViewToolBean.setTitlename(itemcJson.getString("name"));
                        listViewToolBean.setHeigh(itemcJson.getInt("height"));
                        listViewToolBean.setWidth(itemcJson.getInt("width"));
                        listViewToolBean.setMarleft( itemcJson.getInt("left"));
                        listViewToolBean.setMartop(itemcJson.getInt("top"));
                        listViewToolBean.setTitletextsize(itemcJson.getJSONObject("headLine").getInt("fontSize"));
                        listViewToolBean.setTitlehight(itemcJson.getJSONObject("headLine").getInt("height"));
                        listViewToolBean.setTitletextcolor(itemcJson.getJSONObject("headLine").getString("color"));
                        listViewToolBean.setContentlinehight(itemcJson.getJSONObject("contentList").getInt("lineHeight"));
                        listViewToolBean.setContenttextsize(itemcJson.getJSONObject("contentList").getInt("fontSize"));
                        listViewToolBean.setContenttextcolor(itemcJson.getJSONObject("contentList").getString("color"));

                        //  TODO 取到code再去获取数据
                        for(int i =0 ;i<10;i++){
                            ListItemBean listItemBean = new ListItemBean();
                            listItemBean.setIndex(a);
                            listItemBean.setText("测试你每天是是不是"+a);
                            listItemBeen.add(listItemBean);
                        }
                        listViewTool.creatView(listViewToolBean,commonBean,listItemBeen);

                        break;
                    case "s_video":
                        videoViewToolBean.setFocus(true);
                        videoViewToolBean.setHeigh(itemcJson.getInt("height"));
                        videoViewToolBean.setWidth(itemcJson.getInt("width"));
                        videoViewToolBean.setMarleft( itemcJson.getInt("left"));
                        videoViewToolBean.setMartop(itemcJson.getInt("top"));
                        if (itemcJson.getJSONObject("resource").getJSONArray("resourceData").length()!=0) {
                            videoViewToolBean.setUrl(itemcJson.getJSONObject("resource").getJSONArray("resourceData").getJSONObject(0).getString("url"));
                        }else {
                            videoViewToolBean.setUrl("http://192.168.2.120:9901/tsfile/live/0001_2.m3u8?key=txiptv&playlive=1&down=1");
                        }
                        commonBean.setTag(videoViewToolBean);
                        videoViewTool.creatview(videoViewToolBean,commonBean);
                        break;
                }

            }
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }

}

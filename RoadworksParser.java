package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils;

/*  Created by: Afaq Ahmad
        Student number: S1625481
*/
import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.models.Roadwork;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class RoadworksParser extends XmlToJavaParser {
    @Override
    public ArrayList<?> xmlParser(InputStream inputStream) {
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, null);
            String txt = "";
            String tag;
            Roadwork rdw = new Roadwork();
            ArrayList roadworks = new ArrayList();

            int e = parser.getEventType();
            while (e != XmlPullParser.END_DOCUMENT){
                tag = parser.getName();
               switch (e){
                   case XmlPullParser.START_TAG:
                       if(tag.equals("item"))
                           rdw = new Roadwork();
                       break;
                   case XmlPullParser.TEXT:
                       txt = parser.getText();
                       break;
                   case XmlPullParser.END_TAG:
                       switch (tag){
                           case "title":
                               rdw.setTitle(txt);
                               break;
                           case "description":
                               rdw.setDesc(txt);
                               break;
                           case "georss:point":
                               rdw.setCoords(txt.split("\\s"));
                               break;
                           case "pubDate":
                               rdw.setDate(new Date(txt));
                               break;
                           case "item":
                               roadworks.add(rdw);
                               break;
                       }
                       break;
               }
               e = parser.next();
            }
            return roadworks;
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

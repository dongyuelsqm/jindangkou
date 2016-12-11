package com.kingdangkou.weixin.weixiaodan.utils;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dongy on 2016-12-11.
 */
public class JsonHandler {
    public static ArrayList<String> toArrayList(String json){
        JSONArray jsonArray = JSONArray.fromObject(json);
        Collection collection = jsonArray.toCollection(jsonArray);
        ArrayList<String> strings = new ArrayList<String>();
        strings.addAll(collection);
        return strings;
    }
}

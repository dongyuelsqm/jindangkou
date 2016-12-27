package com.kingdangkou.weixin.weixiaodan.model;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by dongy on 2016-12-22.
 */
public class ListResult {
    private boolean result;
    private List objs;

    public ListResult(boolean result, List objs) {
        this.result = result;
        this.objs = objs;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List getObjs() {
        return objs;
    }

    public void setObjs(List objs) {
        this.objs = objs;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}

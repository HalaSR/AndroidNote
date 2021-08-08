package com.example.androidnote.bean;

import com.example.androidnote.ClassifyAdapter;

/**
 * @Author lop
 * @Date 2021/8/8 13:20
 * @Description
 */
public class MainRVBeana {
    private String title;
    private int type;
    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;

    public MainRVBeana(String title, int type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

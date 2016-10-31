package com.sakurawood.popupcard;

/**
 * Created by leesure on 16-10-25.
 */

public class PopcardBean {
    /**
     * the width of parent layout,it is also our popupcardview's width;
     */
    int width;
    /**
     * the height of parent layout ,it is also our popupcardview's height;
     */
    int height;
    /**
     * x is the middle point of triangle;
     */
    int x;
    /**
     * y is the height of triangle;
     */
    int y;
    /**
     * radis is the tilt of triangle;
     */
    int radis;
    /**
     * w is the half width of triangle;
     */
    int w;
    /**
     * round is the round of the popupcardview;
     */
    int round;
    /**
     * the color of the popupcardview;
     */
    int color;
    /**
     * the picture of the popupcardview;
     */
    int picid;
    /**
     * up or down;
     */
    boolean down;

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadis() {
        return radis;
    }

    public void setRadis(int radis) {
        this.radis = radis;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPicid() {
        return picid;
    }

    public void setPicid(int picid) {
        this.picid = picid;
    }
}

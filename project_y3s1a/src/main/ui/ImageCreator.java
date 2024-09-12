package ui;

import javax.swing.*;

// consolidates image information
public class ImageCreator {
    private final int pointx;
    private final int pointy;
    private final ImageIcon image;

    public ImageCreator(int x, int y, ImageIcon image) {
        super();
        this.pointx = x;
        this.pointy = y;
        this.image = image;
    }

    // getters
    public int getPointx() {
        return pointx;
    }

    public int getPointy() {
        return pointy;
    }

    public ImageIcon getImage() {
        return image;
    }
}
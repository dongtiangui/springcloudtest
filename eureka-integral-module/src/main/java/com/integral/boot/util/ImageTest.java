package com.integral.boot.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class ImageTest extends JFrame {

    Image img = null;

    public ImageTest(){

        try {
            File file = new File("/Users/apple/Downloads/IMG_0678.JPG");
            img = ImageIO.read(file);

        }catch (Exception e){

            System.exit(0);
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                System.exit(0);
            }
        });

    }

    public void print(Graphics g){
          g.drawImage(img,200,300,this);
    }


    public static void main(String[] args) {
        ImageTest image = new ImageTest();
        image.setSize(new Dimension(200,300));
        image.setTitle("image");
        image.setVisible(true);
    }
}

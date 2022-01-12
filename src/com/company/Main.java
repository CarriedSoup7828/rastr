package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
public class Main extends JFrame implements MouseListener {
    private BufferedImage image;
    Main() {
        setTitle("Drawing Graphics in Frames");
        setBounds(300,0,1440,1080); // положение и размеры окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try { image = ImageIO.read(new File("nature-wallpaper-07.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addMouseListener(this);
        setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        if (image != null)
            g.drawImage(image,0,0,null);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        WritableRaster r = image.getRaster() ;
        int [] pixel = new int[3];
        for (int y=0 ; y < image.getHeight() ; y++)
            for (int x=0 ; x < image.getWidth() ; x++) {
                r.getPixel(x, y, pixel);

                int R = pixel[2];
                int G = pixel[1];
                int B = pixel[0];
                R=R*2;
                G=G*2;
                B=B*2;

                pixel[0] = B;
                pixel[1] = G;
                pixel[2] = R;
                r.setPixel(x, y, pixel);
            }
        repaint();
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}

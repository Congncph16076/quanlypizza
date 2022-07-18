
package com.mycompany.quanlypizza.common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanelCommon extends JPanel{
    private  Image img;

    public ImagePanelCommon(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanelCommon(Image img) {
        this.img = img;
        
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setSize(size);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
    
    
    
    
    
    
}

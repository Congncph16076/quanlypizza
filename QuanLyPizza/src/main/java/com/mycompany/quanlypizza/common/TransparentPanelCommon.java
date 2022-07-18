package com.mycompany.quanlypizza.common;

import java.awt.LayoutManager;
import javax.swing.JPanel;

public class TransparentPanelCommon extends  JPanel{

    public TransparentPanelCommon() {
        this.setOpaque(false);
    }

    public TransparentPanelCommon(LayoutManager layout) {
        this.setLayout(layout);
    }
    
    
    
    
}

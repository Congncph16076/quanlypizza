package com.mycompany.quanlypizza.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileView;

public class MyFileChooserCommon extends JFileChooser {

    //set kích thước toàn bộ icon
    private static final int ICON_SIZE = 60;
    // blank icon sẽ sử dụng khi load
    private static final Image LOADING_IMAGE = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
    //lọc file
    private final Pattern imageFilePattern = Pattern.compile(".+?\\.(png|jpe?g|gif|tiff?)$", Pattern.CASE_INSENSITIVE);
    //gom rác tự động
    private final Map imageCache = new WeakHashMap();

    public MyFileChooserCommon() {
        super();
    }

    public MyFileChooserCommon(String src) {
        super(src);
    }

    private class ThumbnailView extends FileView {

        private final ExecutorService executor = Executors.newCachedThreadPool();

        public Icon getIcon(File file) {
            if (!imageFilePattern.matcher(file.getName()).matches()) {
                return null;
            }

            synchronized (imageCache) {
                ImageIcon icon = (ImageIcon) imageCache.get(file);

                if (icon == null) {
                    // tạo icon mới với ảnh mặc định
                    icon = new ImageIcon(LOADING_IMAGE);

                    //thêm vào bộ nhớ tạm
                    imageCache.put(file, icon);

                    //load ảnh và sửa icon
                    executor.submit(new ThumbnailIconLoader(icon, file));
                }
                return icon;
            }
        }
    }

    private class ThumbnailIconLoader implements Runnable {

        private final ImageIcon icon;
        private final File file;

        public ThumbnailIconLoader(ImageIcon icon, File file) {
            this.icon = icon;
            this.file = file;
        }

        public void run() {
            //load và scale ảnh sau đó thay ảnh mới bằng ảnh cũ
            ImageIcon newIcon = new ImageIcon(file.getAbsolutePath());
            Image img = newIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            icon.setImage(img);

            //hiển thị ảnh, icon mới
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    repaint();
                }
            });

        }

    }

}

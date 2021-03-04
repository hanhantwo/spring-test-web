package com.cn.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName ImageIoTest
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-01 23:54
 * @Version 1.0
 */
class ImageIoTest {
    @Test
    void test() {

        try {
            //读取图片的io,
            BufferedImage image = ImageIO.read(new File("C:/Users/dxq/Desktop/yunwei/logo.jpg"));
            assertNotNull(image);
            /**
             * 将所有图片放到classpath路径下面，通过类加载方式查找，
             * 这样项目打包以后图片就一直携带，不会出现找不到的现象
             * ClassLoader类将所有class文件加载到内存，
             * ImageIoTest.class.getClassLoader() 可以理解为classpath路径
             */
            BufferedImage image2 = ImageIO.read(ImageIoTest.class.getClassLoader().getResourceAsStream("images/0.gif"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

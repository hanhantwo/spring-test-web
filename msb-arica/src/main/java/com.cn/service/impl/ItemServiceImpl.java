package com.cn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.entity.Item;
import com.cn.mapper.ItemMapper;
import com.cn.service.ItemService;
import com.jfinal.kit.Kv;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.jfinal.template.ext.spring.JFinalViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Autowired
    ItemMapper itemMapper;
    @Value(value = "${nginx.html.root}")
    String htmlRoot;
    @Value(value = "${jfinal.templates.location}")
    String templatesLocation;
    @Override
    public int saveItem(Item item) {
        try {
            return itemMapper.insert(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void generateHtml(int id) {
//        Engine engine = Engine.use();
        Engine engine = JFinalViewResolver.engine;
        engine.setDevMode(true);//是否开启开发模式，生产环境不能true
        engine.setToClassPathSourceFactory();
        //从数据源获取数据
        Item item = itemMapper.selectById(id);
        //前端模板用的键值对
        Kv kv = Kv.by("item", item);
        //文件写入路径
        String fileName = "item" + item.getId() + ".html";
        String filePath = htmlRoot;
        //后面路径直接能被用户访问（写到nginx里）
        File file = new File(filePath + fileName);
        //开始渲染输出文件
        Template template = engine.getTemplate("item.html");
        template.render(kv, file);
    }

    @Override
    public String getFileTemplateString() throws Exception {

//        String file = ClassUtils.getDefaultClassLoader().getResource("item.html").getFile();

        String file = templatesLocation + "item.html";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuffer sb = new StringBuffer();

        String readLine = reader.readLine();
        while (readLine != null) {
            sb.append(readLine).append("\r\n");
            readLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }

    public void saveFileTemplateString(String content) throws Exception {

//        String file = ClassUtils.getDefaultClassLoader().getResource("item.html").getFile();
        String file = templatesLocation + "item.html";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.flush();
        writer.close();
    }
}

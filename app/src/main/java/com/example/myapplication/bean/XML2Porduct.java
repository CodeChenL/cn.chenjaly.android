package com.example.myapplication.bean;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XML2Porduct extends DefaultHandler {
    private List<Product> products;
    private Product product;
    private StringBuffer buffer = new StringBuffer();

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        products = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("product")) {
            product = new Product();
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("product")) {
            //当一组product 标签中的数据 被读取完以后 就讲数据放到products集合中
            products.add(product);
        } else if (localName.equals("id")) {
            //如果读取的是id这个标签 那么就讲id标签中的数据 拿出来 然后放到product对象中
            product.setId(buffer.toString().trim());
            //拿出数据以后 清空这个数据存储器 方便获取下次的数据
            buffer.setLength(0);
        } else if (localName.equals("name")) {
            //如果读取的是name这个标签 那么就讲name标签中的数据 拿出来 然后放到product对象中
            product.setName(buffer.toString().trim());
            //拿出数据以后 清空这个数据存储器 方便获取下次的数据
            buffer.setLength(0);
        } else if (localName.equals("price")) {
            //如果读取的是price这个标签 那么就讲price标签中的数据 拿出来 然后放到product对象中
            product.setPrice(buffer.toString().trim());
            //拿出数据以后 清空这个数据存储器 方便获取下次的数据
            buffer.setLength(0);
        }
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
        super.characters(ch, start, length);
    }
}

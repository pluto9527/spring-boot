package com.jcfc.springboot.entity;

import org.springframework.data.elasticsearch.annotations.Document;

//通过@Document注解来指明 索引库 和 类型
@Document(indexName = "jcfc", type = "book")
public class Book {

    private Integer id;
    private String name;
    private String author;

    public Book() {
    }

    public Book(Integer id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

package com.jcfc.springboot.repository;

import com.jcfc.springboot.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

    public List<Book> findByNameLike(String name);

}

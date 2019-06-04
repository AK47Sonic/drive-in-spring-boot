package com.sonic.elastic.repository;

import com.sonic.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * BookRepository
 *
 * @author Sonic
 * @since 2019/6/3
 */
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

    // spring data 文档
    public List<Book> findByBookNameLike(String bookName);

}

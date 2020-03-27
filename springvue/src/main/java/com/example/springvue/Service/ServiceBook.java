package com.example.springvue.Service;

import com.example.springvue.Dao.BookMapper;
import com.example.springvue.Model.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceBook implements ServiceBookImpentance{
    @Resource
    private BookMapper bookMapper;

    public Book select(Integer id){
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public Book serach1(String name) {
        return bookMapper.searchbook(name);
    }

    @Override
    public List<Book> fandallbook(Integer page) {
        return bookMapper.fandAll(page);
    }
    @Override
    public List<Book> AllBook() {
        return bookMapper.TotalBook();
    }

    @Override
    public int add(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public int change(Book book) {
        return bookMapper.updateByPrimaryKey(book);
    }

    @Override
    public int delete(Integer id) {
        return bookMapper.deleteByPrimaryKey(id);
    }


}

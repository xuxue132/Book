package com.example.springvue.Dao;

import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {
    @Resource
    private BookMapper bookMapper;
    @Test
    public void t(){
        System.out.println(bookMapper.selectByPrimaryKey(2));
    }

}
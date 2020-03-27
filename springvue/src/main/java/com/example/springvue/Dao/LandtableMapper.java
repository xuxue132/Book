package com.example.springvue.Dao;

import com.example.springvue.Model.Landtable;
import com.example.springvue.Model.LandtableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LandtableMapper {
    long countByExample(LandtableExample example);

    int deleteByExample(LandtableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Landtable record);

    int insertSelective(Landtable record);

    List<Landtable> selectByExample(LandtableExample example);

    Landtable selectByPrimaryKey(Integer id);

    Landtable selectByPrimaryKey2(String name,String password);

    Landtable selectByPrimaryKey3(String name);

    List<Landtable> fandAll();

    int updateByExampleSelective(@Param("record") Landtable record, @Param("example") LandtableExample example);

    int updateByExample(@Param("record") Landtable record, @Param("example") LandtableExample example);

    int updateByPrimaryKeySelective(Landtable record);

    int updateByPrimaryKey(Landtable record);
}
package com.example.springvue.Service;

import com.example.springvue.Dao.LandtableMapper;
import com.example.springvue.Model.Landtable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LandService2 implements LandService {
    @Resource
    private LandtableMapper landtableMapper;

    @Override
    public Landtable landSystem(Integer id) {
        return landtableMapper.selectByPrimaryKey(id);
    }

    @Override
    public Landtable LandUser(String name, String password) {
        return landtableMapper.selectByPrimaryKey2(name,password);
    }

    @Override
    public Landtable LandUser2(String name) {
        return landtableMapper.selectByPrimaryKey3(name);
    }
//    @Override
//    public static Landtable LandUser2(String name) {
//        return landtableMapper.selectByPrimaryKey3(name);
//    }

    @Override
    public int insertUser(Landtable landtable) {
        return landtableMapper.insert(landtable);
    }

    @Override
    public List<Landtable> All() {
        return landtableMapper.fandAll();
    }


}

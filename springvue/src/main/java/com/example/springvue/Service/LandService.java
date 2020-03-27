package com.example.springvue.Service;


import com.example.springvue.Model.Landtable;

import java.util.List;

public interface LandService {
    Landtable landSystem(Integer id);

    Landtable LandUser(String name, String password);

    Landtable LandUser2(String name);

    int insertUser(Landtable landtable);

    List<Landtable> All();
}

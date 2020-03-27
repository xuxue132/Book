package com.example.springvue.Controller;

import com.example.springvue.Model.Book;
import com.example.springvue.Model.Landtable;
import com.example.springvue.Service.LandService2;
import com.example.springvue.Service.ServiceBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController

public class ControllerBook {
    @Resource
    private ServiceBook serviceBook;
    @Resource
    private LandService2 landService2;


    @RequestMapping(value = "/get/token",method = RequestMethod.GET)
    public String name(@RequestHeader(value = "Authorization") String token){
        return TokenUtil.TokenUserName(token);
    }

    @RequestMapping(value = "/select/{id}")
    @ResponseBody
    public Book se(@PathVariable("id") Integer id){
        return serviceBook.select(id);
    }

    @GetMapping(value = "/select/all/{page}")
    public List<Book> sel(@PathVariable("page") Integer page){
        List<Book> book = serviceBook.AllBook();
        List<Book> showBook = new ArrayList<>();
        int number = book.size();   //数据总数
        int pag = number/10;        //总页数
        int a = 10;                 //数组长度
        int last = number%10;               //最后一页长长度
        if(last!=0){
            pag=pag+1;
        }
        if(pag == page){
            a=last;
        }
//        System.out.println("此页长度: "+a);
//        Book[] PageBook = new Book[a];
        int h=page*10-10;
        for(int i=0;i<a;i++){
            showBook.add(book.get(h));
//            PageBook[i]= book.get(h);
            h++;
        }
        return showBook;
    }

    //@GetMapping(value = "/select/all")
    @RequestMapping(value = "/select/all",method = RequestMethod.GET)
    public List<Book> NumberAllBook(@RequestHeader(value = "Authorization") String token){
//        @RequestHeader(value = "Authorization") String token;
        return serviceBook.AllBook();
    }

    @PostMapping(value = "/select/add")
    public int NumberAllBook(@RequestBody Book book){
//        List<Book> book1=serviceBook.AllBook();

        boolean flag=true;
        int s=0;
        while (flag) {
            int id = (int) ((Math.random() * 100) + 1);
            Book selectbook = serviceBook.select(id);
            if (selectbook == null) {
                book.setId(id);
                s = serviceBook.add(book);
                flag=false;
            }else {
                flag=true;
            }
        }
        return s;
    }
    @PutMapping(value = "/update")
    public int update(@RequestBody Book book){
        int s=serviceBook.change(book);
        return s;
    }
    @GetMapping(value = "/delete/{id}")
    public int update(@PathVariable("id") Integer id){
        return serviceBook.delete(id);
    }

    @GetMapping(value = "/serach/{name}")
    public Book serach(@PathVariable("name") String name){
        return serviceBook.serach1(name);
    }

    @PostMapping("/land")
    public String landspring(@RequestBody Landtable user) throws JsonProcessingException {
        Landtable book = landService2.LandUser(user.getName(),user.getPassword());
        if(book!=null){
            String token = TokenUtil.sign(user);
            HashMap<String,Object> hs = new HashMap<>();
            if(TokenUtil.verify(token)){
                hs.put("code",200);
            }else {
                hs.put("coe",500);
            }
            hs.put("token",token);
//            hs.put("username",)
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(hs);
        }
        else
            return "密码错误";
    }

    @PostMapping(value = "/add")
    public String addspring(String name,String password){
        Landtable book = landService2.LandUser2(name);
        int s = landService2.All().size()+1;
        if(book!=null)
            return "用户已存在";
        else {
            book=new Landtable();
            book.setId(s);
            book.setName(name);
            book.setPassword(password);
            int as=landService2.insertUser(book);
            return "创建成功";
        }
    }
}

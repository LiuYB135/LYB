package com.liu.test.controller;

import com.liu.test.dao.BookDao;
import com.liu.test.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * 图书控制器
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookDao bookdao;

    /**
     * 查询所有图书
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bookList",bookdao.findAll());
        mv.setViewName("booklist");
        return mv;
    }

    /**
     * 添加图书
     * @param book
     * @return
     */
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public String add(Book book){
        bookdao.save(book);
        return "forward:/book/list";
    }

    /**
     * 根据id查询单体，以作更新操作
     * @param id
     * @return
     */
    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@RequestParam("id") Integer id){
        ModelAndView mv = new ModelAndView();
        mv.addObject("book",bookdao.getOne(id));
        mv.setViewName("bookUpdate");
        return mv;
    }


    /**
     * 修改图书
     * @param book
     * @return
     */
    @RequestMapping("/uodate")
    public String update(Book book){
        bookdao.save(book);
        return "forward:/book/list";
    }

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Integer id){
        bookdao.deleteById(id);
        return "forward:/book/list";
    }

}

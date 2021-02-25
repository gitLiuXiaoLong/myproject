package com.study.example.rest.demo.controller;

import com.study.example.rest.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class FreeMarkerTestController {

    @GetMapping("/findPerson")
    public String findPerson(Model model){

        Person person = new Person();
        person.setId((long) 1);
        person.setName("张三");
        person.setSex("男");
        Person person1 = new Person();
        person1.setId((long) 2);
        person1.setName("张四");
        person1.setSex("男");
        Person person2 = new Person();
        person2.setId((long) 3);
        person2.setName("张五");
        person2.setSex("男");
        Person person3 = new Person();
        person3.setId((long) 4);
        person3.setName("张六");
        person3.setSex("女");

        List<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person1);
        list.add(person2);
        list.add(person3);
        model.addAttribute("persons",list);
        return "views/freeMarkerTest";
    }
}

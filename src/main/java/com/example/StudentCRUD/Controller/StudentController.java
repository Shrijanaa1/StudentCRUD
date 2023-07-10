package com.example.StudentCRUD.Controller;

import com.example.StudentCRUD.Domain.Student;
import com.example.StudentCRUD.Exception.InvalidInputException;
import com.example.StudentCRUD.Exception.StudentNotFoundException;
import com.example.StudentCRUD.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    private String viewHomePage(Model model){
        List<Student> liststudent = service.listAll();
        model.addAttribute("liststudent", liststudent);
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("student", new Student());
        return "new";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student s, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", s); // Add the student object back to the model
            return "new";
        }
        service.save(s);
        return "redirect:/";
    }


    @GetMapping("edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") long id){
        ModelAndView mav = new ModelAndView("new");
        try{
            Student s = service.get(id);
            mav.addObject("student", s);
        }catch (StudentNotFoundException ex){
            ex.getMessage();
        }
        return mav;
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") long id){
        service.delete(id);
        return "redirect:/";
    }

    public ModelAndView handleInvalidNumberFormatException(InvalidInputException ex){
        ModelAndView mnv = new ModelAndView("error");
        mnv.addObject("errorMessage",ex.getMessage());
        return mnv;
    }
}

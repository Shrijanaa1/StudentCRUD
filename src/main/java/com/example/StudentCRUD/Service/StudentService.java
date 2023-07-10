package com.example.StudentCRUD.Service;

import com.example.StudentCRUD.Domain.Student;
import com.example.StudentCRUD.Exception.StudentNotFoundException;
import com.example.StudentCRUD.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> listAll(){
            return studentRepo.findAll();
    }

    public void save(Student s){
        studentRepo.save(s);
    }

    public Student get(Long id) throws StudentNotFoundException{
        return studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student Not Found with ID:" + id));
    }

    public void delete(Long id){
        studentRepo.deleteById(id);
    }
}

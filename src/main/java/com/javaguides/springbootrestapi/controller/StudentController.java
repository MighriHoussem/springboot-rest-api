package com.javaguides.springbootrestapi.controller;

import com.javaguides.springbootrestapi.bean.Student;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1,"Mighri","Houssem");
        //return student;
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header","test-rest-api")
                .body(student);
    }

    @GetMapping("all")
    public ResponseEntity<List<Student>> getStudents() {
        Student student1 = new Student(1,"Mighri", "Houssem");
        Student student2 = new Student(2,"Salah", "Med");
        Student student3 = new Student(3,"Ben Ali", "Rami");
        Student student4 = new Student(4,"Yossef", "Lotfi");
        List<Student> listStudents = new ArrayList<Student>();
        listStudents.add(student1);
        listStudents.add(student2);
        listStudents.add(student3);
        listStudents.add(student4);
        //return listStudents;
        return ResponseEntity.ok(listStudents);
    }

    //Spring boot with Path variable
    //path WS /student/56/Sena/Johan
    @GetMapping("{id}/{first-name}/{last-name}")
    public Student getStudentById(@PathVariable("id") int studentId,
                                  @PathVariable("first-name") String firstName,
                                  @PathVariable("last-name") String lastName) {
        System.out.println("Path variable student id => " + studentId);
        return new Student(studentId, firstName, lastName);
    }

    //Spring boot WS with Request Params
    //path WS /student/query?id=1&lastName=salah&firstName=mohamed
    @GetMapping("query")
    public Student getStudentByIdRequestParam(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName
            ) {
        return new Student(id, firstName,lastName);
    }

    //Spring Boot Rest API Post Request
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> postStudent(@RequestBody Student student) {
        System.out.println("student id: " + student.getId());
        System.out.println("student name: "+ student.getFirstName());
        System.out.println("student last name: " + student.getLastName());
        //return student;
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //Spring boot update Request API PUT Mapping
    @PutMapping("{id}/update")
    public Student updateStudentData (@PathVariable int id ,@RequestBody Student student) {
        System.out.println("id: " + id);
        System.out.println("student update id: " + student.getId());
        System.out.println("student update name: "+ student.getFirstName());
        System.out.println("student update last name: " + student.getLastName());
        return student;
    }

    //Spring Boot delete mapping request REST API
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        System.out.println("Student to delete id: " + id);
        //return "Student deleted successfully !";
        return ResponseEntity.ok("Student deleted successfully !");
    }
}

package com.example.microservicescourse.controller;

import com.example.microservicescourse.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping - Base url at the class lever for all rest methods
//                - For example @RequestMapping("students") will put students in front of all rest methods urls from this class
public class StudentController {

    @GetMapping("student")
    //http://localhost:8080/student
    public Student getStudent(){
        Student student = new Student(
                1,
                "Alex",
                "Mihai"
        );
        return student;
    }

    @GetMapping("students")
    //http://localhost:8080/students
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Andrei", "Avram"));
        students.add(new Student(2, "Alex", "Muntean"));
        students.add(new Student(3, "Daniel", "Popa"));
        students.add(new Student(4, "Bogdan", "Nicolae"));
        return students;
    }


    //Spring Boot Rest API with Path Variable
    //{id} is URI template variable
    //http://localhost:8080/students/1
    @GetMapping("students/{id}")
    public Student studentPathVariable(@PathVariable int id){ //@PathVariable annotation used on a method argument to bind it
                                                               //to the value of a URI template variable
        return new Student(id, "Valentin", "Miron");
    }

    // <=>

    //http://localhost:8080/pathvariable/students/1
    @GetMapping("pathvariable/students/{id}")
    public Student studentPathVariableWithIdArgument(@PathVariable("id") int studentId){ //@PathVariable("id") mark
        // studentId as id when is passed to URI template variable, if is not marks as id but we pass id to the URI
        // template variable we will get an exception
        return new Student(studentId, "Valentin", "Miron");
    }

    //http://localhost:8080/students/1/andrei/nicolau
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariableWithArguments(@PathVariable("id") int studentId,
                                                    @PathVariable("first-name") String firstName,
                                                    @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName, lastName);
    }

    //Spring boot Rest API with Request Param
    //http://localhost:8080/students?id=1 ( id=1 - query parameter )
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id, "Mihai", "Egescu");
    }

    //http://localhost:8080/students/query?id=1&firstName=Ale&lastName=Popa
    @GetMapping("students/query")
    public Student studentRequestVariableWithArguments(@RequestParam int id,
                                                       @RequestParam String firstName,
                                                       @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }

    //Difference between @PathVariable and @RequestParam is:
    //          @PathVariable annotation is used to bind the value of URI template variable into method argument
    //          @RequestParam annotation to extract the value of query parameters in a request URL.

    /*
    https://medium.com/javarevisited/difference-between-requestparam-and-pathvariable-in-spring-mvc-873bd6330bce
     */


    //Spring Boot REST API that handles HTTP POST Request - creating new resource
    //@PostMapping annotation is used for mapping HTTP POST request onto specific handler method
    //http://localhost:8080/students/create
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){ //@RequestBody annotation is responsible for retrieving
        System.out.println(student.getId());              //the HTTP request body and automatically converting it to
        System.out.println(student.getFirstName());       //the Java object. @RequestBody annotation internally uses
        System.out.println(student.getLastName());        //Spring provided HttpMessageConverter to convert JSON
        return student;                                   //into Java object.
    }


    //Spring Boot REST API that handles HTTP PUT Request - updating existing resource
    //@PutMapping annotation is used for mapping HTTP PUT request onto specific handler method
    //http://localhost:8080/students/1/update
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student,
                                 @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Spring Boot REST API that handles HTTP DELETE Request - deleting the existing resource
    //@DeleteMapping annotation is used for mapping HTTP DELETE request onto specific handler method
    //http://localhost:8080/students/1/delete
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student deleted successfully";
    }

    /*
                ResponseEntity:
          -ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to
          fully configure the HTTP response.
          -If we want to use it, we have to return it from the endpoint; Spring takes care of rest.
          -ResponseEntity is a generic type. Consequently, we can use any type as the response body.
     */

    //http://localhost:8080/responseentity/student
    @GetMapping("responseentity/student")
    public ResponseEntity<Student> getStudentResponseEntity(){
        Student student = new Student(
                1,
                "Alex",
                "Mihai"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        // <=>
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/responseentity/header/student
    @GetMapping("responseentity/header/student")
    public ResponseEntity<Student> getStudentResponseEntityCustomHeader(){
        Student student = new Student(
                1,
                "Alex",
                "Mihai"
        );
        return ResponseEntity.ok().header("custom-header", "Alexandru") // Send request in Postman then
                .body(student);                                             //go to Headers and see custom-header value
    }




}

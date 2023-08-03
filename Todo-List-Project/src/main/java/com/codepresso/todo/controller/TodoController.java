package com.codepresso.todo.controller;

import java.util.List;

import com.codepresso.todo.service.TodoService;
import com.codepresso.todo.vo.ResultDto;
import com.codepresso.todo.vo.Todo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController // Data를 응답하는 Controller
public class TodoController {

    //todo TodoService를 활용하기 위한 의존성 주입 코드
    private final TodoService todoService;
    // 생성자 매개변수
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo") // 간소화된 Annotation
    @ResponseBody // 자바 객체를 HTTP 응답 본문의 객체로 변환하여 클라이언트에 전송
    public List<Todo> getTodoList(){
        List<Todo> list = todoService.getTodoList();
        return list;
    }

    @PostMapping("/todo") // 간소화된 Annotation
    @ResponseBody
    // Object : 최상위 조상 클래스
    public Object addTodo(HttpServletResponse response, @RequestBody Todo todoParam) {
        Todo todo = new Todo(todoParam.getContent());
        todoService.addTodo(todo);
        // Http 응답 코드를 넣어줌 (디폴트 값 = 200)
        response.setStatus(HttpServletResponse.SC_OK);
        return new ResultDto(200, "Success");
    }

    @DeleteMapping("todo/{id}")
    public Object deleteTodo(HttpServletResponse response, @PathVariable("id") String id) {
        // Integer로 변환해줌
        todoService.deleteTodo(Integer.parseInt(id));

        response.setStatus(HttpServletResponse.SC_OK);
        return new ResultDto(200, "Success");
    }

}

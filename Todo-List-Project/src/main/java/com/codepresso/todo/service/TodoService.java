package com.codepresso.todo.service;

import java.util.List;

import com.codepresso.todo.vo.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final List<Todo> todoList;

    public TodoService(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public void addTodo(Todo todo) {
        todo.setId(todoList.size());
        todoList.add(todo);
    }

    public List<Todo> getTodoList(){
        // todoList 정보를 반환
        return todoList;
    }

    public void deleteTodo(int index) {
        // index에 해당하는 원소를 삭제
        todoList.remove(index);
    }
}

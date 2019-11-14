package com.alipay.demo.pojo;

public class GetTodosResponse extends SuccessResponse {
    private Iterable<Task> todoList;

    public Iterable<Task> getTodoList() {
        return todoList;
    }

    public void setTodoList(Iterable<Task> todoList) {
        this.todoList = todoList;
    }
}

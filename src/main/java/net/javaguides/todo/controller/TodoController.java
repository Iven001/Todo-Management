package net.javaguides.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;

@RestController
@RequestMapping("api/todoes")
@AllArgsConstructor
public class TodoController {
	
	private TodoService todoService;
	
	
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		
		TodoDto savedTodo = todoService.addTodo(todoDto);
		
		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
		
		TodoDto todoDto = todoService.getTodo(todoId);
		
		
		return new ResponseEntity<>(todoDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodoes() {
		
		List<TodoDto> todoes = todoService.getAllTodoes();
		
		return ResponseEntity.ok(todoes);
	}

	@PutMapping
	public ResponseEntity<TodoDto> updateTodo (@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
		
		TodoDto updateTodo = todoService.updatedTod(todoDto, todoId);
		
		return ResponseEntity.ok(updateTodo);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo (@PathVariable("id") Long todoId) {
		
		todoService.deleteTodo(todoId);
		
		return ResponseEntity.ok("Todo deleted successfully!.");
	}
	
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDto> completeTod(@PathVariable("id")  Long todoId) {
		
		TodoDto updatedTodo = todoService.completeTodo(todoId);
		
		return ResponseEntity.ok(updatedTodo);
	}
	
	@PatchMapping("{id}/incomplete")
	public ResponseEntity<TodoDto> inCompleteTod(@PathVariable("id")  Long todoId) {
		
		TodoDto updatedTodo = todoService.inCompleteTodo(todoId);
		
		return ResponseEntity.ok(updatedTodo);
	}
	
}

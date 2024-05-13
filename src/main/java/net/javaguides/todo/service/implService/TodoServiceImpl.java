package net.javaguides.todo.service.implService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.repository.TodoRepository;
import net.javaguides.todo.service.TodoService;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepo;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
		Todo todo = new Todo();
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		
		Todo saveTodo = todoRepo.save(todo);
		
		TodoDto savedTodoDto = new TodoDto();
		savedTodoDto.setId(saveTodo.getId());
		savedTodoDto.setTitle(saveTodo.getTitle());
		savedTodoDto.setDescription(saveTodo.getDescription());
		savedTodoDto.setCompleted(saveTodo.isCompleted());
		
		return savedTodoDto;
	}

}

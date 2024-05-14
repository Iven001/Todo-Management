package net.javaguides.todo.service.implService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.TodoRepository;
import net.javaguides.todo.service.TodoService;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepo;
	
	private ModelMapper modelMapper;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
		Todo todo = modelMapper.map(todoDto, Todo.class);
				
		
		Todo saveTodo = todoRepo.save(todo);
		
		
		TodoDto savedTodoDto = modelMapper.map(saveTodo, TodoDto.class);;
		
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		
		Todo todo = todoRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:"+id));
		
		return modelMapper.map(todo, TodoDto.class);
	}

}

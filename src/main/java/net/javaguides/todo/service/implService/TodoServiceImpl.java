package net.javaguides.todo.service.implService;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<TodoDto> getAllTodoes() {
		
		List<Todo> todoes = todoRepo.findAll();
		
		
		return todoes.stream().map((todo) -> modelMapper.map(todoes, TodoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDto updatedTod(TodoDto todoDto, Long id) {
		
		Todo todo = todoRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:"+id));
		
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		
		Todo updatedDto = todoRepo.save(todo);
		
		return modelMapper.map(updatedDto,TodoDto.class );
	}

	@Override
	public void deleteTodo(Long id) {
		
		Todo todo = todoRepo.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id"+id));
	
		todoRepo.delete(todo);
		
	}

	@Override
	public TodoDto completeTodo(Long id) {
		// TODO Auto-generated method stub
		Todo todo = todoRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id"+id));
		
		todo.setCompleted(Boolean.TRUE);
		
		Todo updatedTodo = todoRepo.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		
		Todo todo = todoRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id"+id));
		
		todo.setCompleted(Boolean.FALSE);
		
		Todo updatedTodo = todoRepo.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}

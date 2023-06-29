import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { CreateTodoDto, UpdateTodoDto } from 'src/interface/todo-dto.interface';
import { Todo } from 'src/schema/todo.schema';
import { TodoService } from './todo.service';

@Controller('todo')
export class TodoController {
  constructor(private todoService: TodoService) {}
  @Get()
  public async getTodo() {
    return await this.todoService.findAll();
  }

  @Post()
  async postTodo(@Body() todo: CreateTodoDto): Promise<Todo> {
    return await this.todoService.create(todo);
  }

  @Put(':id')
  async putTodo(@Param('id') id: string, @Body() todo: UpdateTodoDto) {
    return await this.todoService.update(id, todo);
  }

  @Delete(':id')
  async deletTodo(@Param('id') id: string) {
    return await this.todoService.delete(id);
  }
}

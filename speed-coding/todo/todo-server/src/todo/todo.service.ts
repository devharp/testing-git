import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CreateTodoDto, UpdateTodoDto } from 'src/interface/todo-dto.interface';
import { Todo, TodoDocument } from 'src/schema/todo.schema';

@Injectable()
export class TodoService {
  constructor(@InjectModel(Todo.name) private todoModel: Model<TodoDocument>) {}

  public findAll(): any {
    return this.todoModel
      .find({}, { __v: 0 }, { new: true })
      .lean()
      .exec()
      .then((todos) =>
        todos.map(({ _id: id, ...rest }) => ({
          id: id.toString(),
          ...rest,
        })),
      );
  }

  public create(todo: CreateTodoDto) {
    return new this.todoModel(todo).save().then((savedTodo) => ({
      ...savedTodo.toObject(),
      id: savedTodo._id,
      __v: undefined,
      _id: undefined,
    }));
  }

  public update(id: string, todo: UpdateTodoDto) {
    return this.todoModel.findByIdAndUpdate(id, todo, { new: true }).exec();
  }

  public delete(id: string) {
    return this.todoModel.findByIdAndDelete(id).exec();
  }
}

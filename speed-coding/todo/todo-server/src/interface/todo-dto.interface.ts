export interface CreateTodoDto {
  task: string;
  completed?: boolean;
}

export class UpdateTodoDto {
  task?: string;
  completed?: boolean;
}

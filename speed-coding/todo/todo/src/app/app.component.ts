import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { TodoDto } from 'src/interface/todo-response.interface';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  public todoForm: FormGroup = this.fb.group({
    task: ['', Validators.required]
  })
  
  constructor(public appService: AppService, private fb: FormBuilder){}

  public onAddTask(): void {
    this.appService.createTask<TodoDto>(this.todoForm?.get('task')?.value)
      .subscribe(
        (savedTodo: TodoDto) => {
          this.appService.setTodos();
          this.todoForm.reset()
        },
        (error: HttpErrorResponse) => console.error(error)
      )
  }
}

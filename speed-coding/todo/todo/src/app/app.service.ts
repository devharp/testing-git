import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { TodoDto } from 'src/interface/todo-response.interface';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  public todos: Array<TodoDto> = [];
  private link = 'http://localhost:3000/todo';

  constructor( private httpService: HttpClient ) {
    this.setTodos();
  }

  public setTodos() {
    this.httpService.get<Array<TodoDto>>(this.link)
    .pipe(tap((todos: Array<TodoDto>) => this.todos = todos))
    .subscribe();
  }

  public createTask<T>(task: string): Observable<T> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const payload = { task, completed: false };
    return this.httpService.post<T>(this.link, payload, { headers })
  }
}

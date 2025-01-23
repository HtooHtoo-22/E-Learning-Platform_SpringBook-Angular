import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../../models/user';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  private apiUrl = 'http://localhost:8081/api/users/teachers';
  constructor(private http : HttpClient) { }

  getTeacher(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/edit/${id}`);
  }
}

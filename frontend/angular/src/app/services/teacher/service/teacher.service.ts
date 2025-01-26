import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../../models/user';
import { ApiResponse } from '../../../models/apiresponse';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  private apiUrl = 'http://localhost:8081/api/users/teachers';
  constructor(private http : HttpClient) { }

  getTeacher(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/edit/${id}`);
  }
  updateTeacher(id: number, teacher: User): Observable<ApiResponse<User>> {
    return this.http.put<ApiResponse<User>>(`${this.apiUrl}/update/${id}`, teacher);

  }
  createTeacher(teacher: User): Observable<ApiResponse<User>> {
    return this.http.post<ApiResponse<User>>(`${this.apiUrl}/create`, teacher);
  }
}

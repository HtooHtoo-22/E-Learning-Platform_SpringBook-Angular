import { Injectable } from '@angular/core';
import { Student } from '../models/student';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = "http://localhost:8081/api/users";
      constructor(private http : HttpClient) { }
      getAllActive():Observable<Student[]>{
          return this.http.get<Student[]>(this.url + "/getAllActiveStudents");
        }
      getAllActiveTeachers():Observable<User[]>{ 
        return this.http.get<User[]>(this.url + "/getAllActiveTeachers");
      }
}

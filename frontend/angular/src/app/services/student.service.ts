import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../models/student';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
 private url = "http://localhost:8081/api/users";
    constructor(private http : HttpClient) { }
    getAllActive():Observable<Student[]>{
        return this.http.get<Student[]>(this.url + "/getAllActiveStudents");
      }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {
  private url = "http://localhost:8081/api/student";
  constructor(private http : HttpClient) { }
  getAllActive():Observable<Student[]>{
    return this.http.get<Student[]>(this.url + "/getAllActiveStudents");
  }
}

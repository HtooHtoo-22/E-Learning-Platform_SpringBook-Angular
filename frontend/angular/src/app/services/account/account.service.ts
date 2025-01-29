import { Injectable } from '@angular/core';
import { LoginDTO } from '../../models/loginDTO';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../models/apiresponse';
import { User } from '../../models/user';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private apiUrl = 'http://localhost:8081/api/account';

  constructor(private http : HttpClient) { }

  login(loginDTO: LoginDTO): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/login`, loginDTO, {
      responseType: 'text' as 'json'  // Specify that the response is a plain text string (JWT token)
    });
  }

}

import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { LoginDTO } from '../../../models/loginDTO';
import { AccountService } from '../../../services/account/account.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm !: FormGroup;
  message: string = '';
  isSuccess: boolean = false;
  isLoading: boolean = false;
  loginDTO !: LoginDTO ;
  isAuthenticated: boolean = false;
  constructor(private fb: FormBuilder , private accountService:AccountService, private router: Router) {}
  

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(2)]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    this.isLoading = true;
    this.message = '';
    this.isSuccess = false;
    this.loginDTO = this.loginForm.value;
    console.log(this.loginDTO);
    
    // Simulate API call (replace with actual login service)
    this.accountService.login(this.loginDTO).subscribe({
      next: (token: string) => {
        this.isLoading = false;
        this.message = 'Login successful!';
        this.isSuccess = true;
    
        // Store the token in localStorage or sessionStorage
        localStorage.setItem('authToken', token);
        this.isAuthenticated = true;
  // Replace 'authToken' with your actual key
        this.router.navigate(['/teacher']).then(() => {
          console.log('Navigation successful to /teacher');
        }).catch((error) => {
          console.error('Navigation error:', error);
        });
      },
      error: (error) => {
        this.isLoading = false;
        // Log the error for debugging
        console.error('Login error: ', error);
    
        // Handle different types of error responses
        if (error.status === 401) {
          this.message = 'Invalid credentials. Please try again.';
        } else {
          this.message = error.error?.message || 'Login failed. Please try again.';
        }
        this.isSuccess = false;
      }
    });
    
  }
}

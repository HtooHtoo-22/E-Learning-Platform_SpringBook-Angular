import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { LoginDTO } from '../../../models/loginDTO';
import { AccountService } from '../../../services/account/account.service';

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

  constructor(private fb: FormBuilder , private accountService:AccountService) {}

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
      next: (response) => {
        this.isLoading = false;
        this.message = 'Login successful!';
        this.isSuccess = true;

        // Navigate to another page (e.g., dashboard) after successful login
        
      },
      error: (error) => {
        this.isLoading = false;
        this.message = error.error?.message || 'Login failed. Please try again.';
        this.isSuccess = false;
      }
    }); // Simulate a 2-second delay for API call
  }
}

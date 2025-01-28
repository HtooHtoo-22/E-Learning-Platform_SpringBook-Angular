import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountService } from '../../../services/account.service';

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

  constructor(private fb: FormBuilder , private accountService:AccountService) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    this.isLoading = true;
    this.message = '';
    this.isSuccess = false;

    // Simulate API call (replace with actual login service)
    this.accountService.login(this.loginForm.value).subscribe({
      next: (response) => {
        this.isLoading = false;
        this.message = 'Login successful!';
        this.isSuccess = true;

        // Navigate to another page (e.g., dashboard) after successful login
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        this.isLoading = false;
        this.message = error.error?.message || 'Login failed. Please try again.';
        this.isSuccess = false;
      }
    }); // Simulate a 2-second delay for API call
  }
}

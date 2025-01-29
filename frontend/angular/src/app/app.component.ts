import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  isAuthenticated: boolean = false;

ngOnInit(): void {
  // Check if the user has a valid token in localStorage (or your chosen storage)
  this.isAuthenticated = !!localStorage.getItem('authToken');
}
}

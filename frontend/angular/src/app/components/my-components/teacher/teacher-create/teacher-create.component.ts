import { Component } from '@angular/core';
import { TeacherService } from '../../../../services/teacher/service/teacher.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../../models/user';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-teacher-create',
  standalone: false,
  
  templateUrl: './teacher-create.component.html',
  styleUrl: './teacher-create.component.css'
})
export class TeacherCreateComponent {
  teacherForm !: FormGroup;
  teacher !: User ;
  message !: string;
  error: boolean = false;
  constructor(private teacherservice: TeacherService, private router: Router,private route: ActivatedRoute) {
    this.teacherForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      gender: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required])
      
    });
  }
  onSubmit(){
    this.teacher = this.teacherForm.value;
    this.teacherservice.createTeacher(this.teacher).subscribe({
      next: (response: any) => {
        this.message = response.message;
        this.error = false;
        alert("Teacher created successfully");
        setTimeout(() => {
          this.router.navigate(['/teacher']); // Navigate after a delay
        }, 2000); 
      },
      error: (error: any) => {
        this.message = error.error.message;
        this.error = true;
        alert("Error in creating teacher");
      }
    });
    
  }

}

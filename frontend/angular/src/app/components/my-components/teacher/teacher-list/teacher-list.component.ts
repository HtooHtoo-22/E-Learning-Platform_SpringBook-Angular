import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../../../models/user';
import { UserService } from '../../../../services/user.service';
import { TeacherService } from '../../../../services/teacher/service/teacher.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-teacher-list',
  standalone: false,
  
  templateUrl: './teacher-list.component.html',
  styleUrl: './teacher-list.component.css'
})
export class TeacherListComponent {
    teachers$ !: Observable<User[]> ;
    message !: string;
    constructor(private userService: UserService, private teacherService: TeacherService, private router: Router) { }
    
    ngOnInit() {
        this.teachers$ = this.userService.getAllActiveTeachers();
        console.log(this.teachers$);       
    }
    suspendTeacher(teacherId : number){
      this.teacherService.suspendTeacher(teacherId).subscribe({
        next: (response: { message: string }) => {
            this.message = response.message;
            
            // Navigate after a delay (if needed)
            setTimeout(() => {
                this.router.navigate(['/teacher/create']);
            }, 2000); // Adjust the delay as needed
        },
        error: (error: { error: { message: string } }) => {
            this.message = error.error.message;
            alert("Error in suspending teacher: " + this.message); // Provide more specific feedback
        }
    });
      
      }
    }


import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../../../models/user';
import { UserService } from '../../../../services/user.service';
import { TeacherService } from '../../../../services/teacher/service/teacher.service';
import { response } from 'express';

@Component({
  selector: 'app-teacher-list',
  standalone: false,
  
  templateUrl: './teacher-list.component.html',
  styleUrl: './teacher-list.component.css'
})
export class TeacherListComponent {
    teachers$ !: Observable<User[]> ;
    message !: string;
    constructor(private userService: UserService,private teacherService : TeacherService) { }
    ngOnInit() {
        this.teachers$ = this.userService.getAllActiveTeachers();
        console.log(this.teachers$);       
    }
    suspendTeacher(teacherId : number){
      this.teacherService.suspendTeacher(teacherId).subscribe({
        next: (response: any) => {
          this.message = response.message;
          // setTimeout(() => {
          //   this.router.navigate(['/teacher']); // Navigate after a delay
          // }, 2000); 
        },
        error: (error: any) => {
          this.message = error.error.message;
          alert("Error in creating teacher");
        }
      });
      
      })
    }
}

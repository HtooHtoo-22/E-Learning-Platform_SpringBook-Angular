import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../../../models/user';
import { UserService } from '../../../../services/user.service';

@Component({
  selector: 'app-teacher-list',
  standalone: false,
  
  templateUrl: './teacher-list.component.html',
  styleUrl: './teacher-list.component.css'
})
export class TeacherListComponent {
    teachers$ !: Observable<User[]> ;
    constructor(private userService: UserService) { }
    ngOnInit() {
        this.teachers$ = this.userService.getAllActiveTeachers();
        console.log(this.teachers$);       
    }
}

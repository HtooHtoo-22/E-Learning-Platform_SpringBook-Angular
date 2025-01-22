import { Component } from '@angular/core';
import { StudentServiceService } from '../../../../services/student-service.service';
import { Observable } from 'rxjs';
import { Student } from '../../../../models/student';
import { StudentService } from '../../../../services/student.service';

@Component({
  selector: 'app-student-list',
  standalone: false,
  
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent {
  students$ !: Observable<Student[]> ;
  students:Student[]=[];
  constructor(private studentService: StudentService) { }
  ngOnInit() {
    this.students$ = this.studentService.getAllActive();
    console.log("hi");
    
    console.log(this.students$);
    
    this.studentService.getAllActive().subscribe(
      (data: Student[]) => {
        this.students = data; // Assign the data to the students property
        console.log("Students data:", this.students); // Log the data
      },
      (error) => {
        console.error("Error fetching students:", error); // Log any errors
      }
    );
    
  }
}

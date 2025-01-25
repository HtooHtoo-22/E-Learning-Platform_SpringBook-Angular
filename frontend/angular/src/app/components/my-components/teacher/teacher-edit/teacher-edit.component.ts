import { Component } from '@angular/core';
import { TeacherService } from '../../../../services/teacher/service/teacher.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../../models/user';

@Component({
  selector: 'app-teacher-edit',
  standalone: false,
  
  templateUrl: './teacher-edit.component.html',
  styleUrl: './teacher-edit.component.css'
})
export class TeacherEditComponent {
  teacherForm: FormGroup;
  teacherId: number = 0;
  teacher !: User ;
  teacherData !: User ;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private teacherService: TeacherService
  ) {
    // Initialize the form with default values
    this.teacherForm = new FormGroup({
      name: new FormControl('', Validators.required), // Name is required
      city: new FormControl('', [Validators.required, Validators.minLength(3)]), // City is required and must have at least 3 characters
      gender: new FormControl('', [Validators.required]), // Gender is required
    });
  }
  ngOnInit(): void {
    this.teacherId = this.route.snapshot.params['id'];
      console.log('Editing teacher with id: ', this.teacherId);
      this.teacherService.getTeacher(this.teacherId).subscribe((data) => {
        this.teacherData = data;
        this.teacherForm.patchValue({
          name: data.name,
          city: data.city,
          gender: data.gender,

        });
      });
  }

  onSubmit(): void {
    if(this.teacherForm.valid) {
      const updatedValues = this.teacherForm.value; // Get form values
      this.teacherData = {
        ...this.teacherData, // Spread the original data
        ...updatedValues,   // Override with form values
      };
      console.log(this.teacherData);  
      this.teacherService.updateTeacher(this.teacherId, this.teacherData).subscribe(() => {
        this.router.navigate(['/teacher']);
      });
    }
    
  }
}

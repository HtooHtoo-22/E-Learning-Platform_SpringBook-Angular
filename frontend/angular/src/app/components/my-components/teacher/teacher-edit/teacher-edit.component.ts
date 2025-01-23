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
  isEditing = false;
  teacherId: number | null = null;
  teacher: User | null = null;

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
    console.log(this.teacherId);
    

    if (this.teacherId) {
      console.log('Editing teacher with id: ', this.teacherId);
      
      this.isEditing = true;
      this.teacherService.getTeacher(this.teacherId).subscribe((data) => {
        this.teacher = data;
        this.teacherForm.patchValue({
          name: this.teacher.name,
          city: data.city,
          gender: data.gender,
        });
      });
    } else {
      // Set default values for a new teacher (if needed)
      console.log("fafaf");
      
      this.teacherForm.patchValue({
        name: 'Default Name',
        city: 'Default City',
        gender: 'Male',
      });
    }
    // if (this.teacherId) {
    //   this.isEditing = true;
    //   this.teacherService.getTeacher(this.teacherId).subscribe({
    //     next: (data) => {
    //       this.teacher = data;
    //       console.log(data);
          
    //       this.teacherForm.patchValue(data); // Bind data to the form
    //     },
    //     error: (err) => {
    //       console.error('Error fetching teacher:', err);
    //     },
    //   });
    // }
  }

  onSubmit(): void {
    // if (this.teacherForm.valid) {
    //   const updatedTeacher = { ...this.teacherForm.value, id: this.teacherId };

    //   if (this.isEditing) {
    //     // Update the teacher
    //     this.teacherService.updateTeacher(updatedTeacher).subscribe(() => {
    //       this.router.navigate(['/teachers']); // Navigate back to the list after updating
    //     });
    //   } else {
    //     // Add a new teacher (if needed)
    //     this.teacherService.addTeacher(updatedTeacher).subscribe(() => {
    //       this.router.navigate(['/teachers']);
    //     });
    //   }
    // }
    
  }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentListComponent } from './components/my-components/student/student-list/student-list.component';
import { TeacherListComponent } from './components/my-components/teacher/teacher-list/teacher-list.component';
import { TeacherEditComponent } from './components/my-components/teacher/teacher-edit/teacher-edit.component';

const routes: Routes = [
  {path: '',component: StudentListComponent},
  {path: 'teacher',component: TeacherListComponent},
  {path: 'teacher/editteacher/:id',component: TeacherEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

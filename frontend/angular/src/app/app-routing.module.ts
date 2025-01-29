import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentListComponent } from './components/my-components/student/student-list/student-list.component';
import { TeacherListComponent } from './components/my-components/teacher/teacher-list/teacher-list.component';
import { TeacherEditComponent } from './components/my-components/teacher/teacher-edit/teacher-edit.component';
import { TeacherCreateComponent } from './components/my-components/teacher/teacher-create/teacher-create.component';
import { LoginComponent } from './components/my-components/login/login.component';

const routes: Routes = [
  {path: '',component: LoginComponent},
  {path: 'login',component: LoginComponent},
  {path: 'teacher',component: TeacherListComponent},
  {path: 'teacher/editteacher/:id',component: TeacherEditComponent},
  {path: 'teacher/create',component: TeacherCreateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

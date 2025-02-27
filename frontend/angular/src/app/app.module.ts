import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Add this line

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { StudentListComponent } from './components/my-components/student/student-list/student-list.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { provideHttpClient } from '@angular/common/http';
import { TeacherListComponent } from './components/my-components/teacher/teacher-list/teacher-list.component';
import { TeacherEditComponent } from './components/my-components/teacher/teacher-edit/teacher-edit.component';
import { TeacherCreateComponent } from './components/my-components/teacher/teacher-create/teacher-create.component';
import { LoginComponent } from './components/my-components/login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    MainContentComponent,
    StudentListComponent,
    SidebarComponent,
    TeacherListComponent,
    TeacherEditComponent,
    TeacherCreateComponent,
    LoginComponent
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // Add this line
    ReactiveFormsModule // Add this line
  ],
  providers: [
    provideClientHydration(withEventReplay()) ,
    provideHttpClient()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Add this line

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { StudentListComponent } from './components/my-components/student/student-list/student-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    MainContentComponent,
    StudentListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // Add this line
    ReactiveFormsModule // Add this line
  ],
  providers: [
    provideClientHydration(withEventReplay()) // This is correct
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { RecoverComponent } from './auth/recover/recover.component';
import { ProfileComponent } from './auth/profile/profile.component';

import { DashboardComponent as AdminDashboardComponent } from './admin/dashboard/dashboard.component';
import { LabsComponent } from './admin/labs/labs.component';
import { UsersComponent } from './admin/users/users.component';

import { DashboardComponent as PatientDashboardComponent } from './patient/dashboard/dashboard.component';
import { ResultsComponent } from './patient/results/results.component';

const routes: Routes = [
  { path: '', redirectTo: 'auth/login', pathMatch: 'full' },

  // Auth
  { path: 'auth/login', component: LoginComponent },
  { path: 'auth/register', component: RegisterComponent },
  { path: 'auth/recover', component: RecoverComponent },
  { path: 'auth/profile', component: ProfileComponent },

  // Admin
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'admin/labs', component: LabsComponent },
  { path: 'admin/users', component: UsersComponent },

  // Paciente
  { path: 'patient', component: PatientDashboardComponent },
  { path: 'patient/results', component: ResultsComponent },

  { path: '**', redirectTo: 'auth/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

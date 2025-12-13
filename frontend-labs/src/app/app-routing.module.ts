
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// ADMIN
import { AdminComponent } from './admin/admin.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { UsersComponent } from './admin/users/users.component';
import { LabsComponent } from './admin/labs/labs.component';

// AUTH
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { RecoverComponent } from './auth/recover/recover.component';

// PATIENT
import { PatientComponent } from './patient/patient.component';

// RESULTS PAGES
import { ResultsListComponent } from './pages/results/results-list/results-list.component';
import { ResultsFormComponent } from './pages/results/results-form/results-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'admin/dashboard', pathMatch: 'full' },

  // AUTH
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'recover', component: RecoverComponent },

  // ADMIN
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'usuarios', component: UsersComponent },
      { path: 'laboratorios', component: LabsComponent },
      { path: 'resultados', component: ResultsListComponent },
      { path: 'resultados/nuevo', component: ResultsFormComponent }
    ]
  },

  // PATIENT
  {
    path: 'paciente',
    component: PatientComponent,
    children: [
      { path: 'resultados', component: ResultsListComponent }
    ]
  },

  { path: '**', redirectTo: 'admin/dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

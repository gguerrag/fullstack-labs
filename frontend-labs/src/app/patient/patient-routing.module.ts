import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent as PatientDashboardComponent } from './dashboard/dashboard.component';
import { ResultsComponent } from './results/results.component';

const routes: Routes = [
  { path: '', component: PatientDashboardComponent },
  { path: 'results', component: ResultsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRoutingModule {}

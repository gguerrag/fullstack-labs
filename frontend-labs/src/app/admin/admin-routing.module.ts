import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent as AdminDashboardComponent } from './dashboard/dashboard.component';
import { LabsComponent } from './labs/labs.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  { path: '', component: AdminDashboardComponent },
  { path: 'labs', component: LabsComponent },
  { path: 'users', component: UsersComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}

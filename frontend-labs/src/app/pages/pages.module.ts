import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ResultsListComponent } from './results/results-list/results-list.component';
import { ResultsFormComponent } from './results/results-form/results-form.component';

@NgModule({
  declarations: [
    ResultsListComponent,
    ResultsFormComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    ResultsListComponent,
    ResultsFormComponent
  ]
})
export class PagesModule { }

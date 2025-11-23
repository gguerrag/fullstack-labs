import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { LabsService } from 'src/app/core/services/labs.service';
import { ResultsService } from 'src/app/core/services/results.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  totalUsuarios = 0;
  totalLabs = 0;
  totalResultados = 0;

  constructor(
    private authService: AuthService,
    private labsService: LabsService,
    private resultsService: ResultsService
  ) {}

  ngOnInit(): void {
    this.totalUsuarios = this.authService.getAllUsers().length;
    this.totalLabs = this.labsService.getLabs().length;
    this.totalResultados = this.resultsService.getAll().length;
  }
}

import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { ResultsService } from 'src/app/core/services/results.service';
import { AnalysisResult } from 'src/app/core/models/result.model';
import { User } from 'src/app/core/models/user.model';

@Component({
  selector: 'app-patient-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  user: User | null = null;
  results: AnalysisResult[] = [];

  totalResultados = 0;
  totalCompletados = 0;
  totalPendientes = 0;

  constructor(
    private authService: AuthService,
    private resultsService: ResultsService
  ) {}

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();

    if (this.user) {
      this.results = this.resultsService.getResultsByUser(this.user.id);

      this.totalResultados = this.results.length;
      this.totalCompletados = this.results.filter(
        r => r.estado === 'Completado'
      ).length;
      this.totalPendientes = this.results.filter(
        r => r.estado === 'Pendiente'
      ).length;
    }
  }
}

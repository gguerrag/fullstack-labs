import { Component, OnInit } from '@angular/core';
import { ResultsService } from 'src/app/core/services/results.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { AnalysisResult } from 'src/app/core/models/result.model';
import { User } from 'src/app/core/models/user.model';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html'
})
export class ResultsComponent implements OnInit {

  results: AnalysisResult[] = [];
  user: User | null = null;

  constructor(
    private resultsService: ResultsService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();
    if (this.user) {
      this.results = this.resultsService.getResultsByUser(this.user.id);
    }
  }
}

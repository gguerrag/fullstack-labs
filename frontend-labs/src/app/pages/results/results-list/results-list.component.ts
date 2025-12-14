import { Component, OnInit } from '@angular/core';
import { ResultsService, Resultado } from 'src/app/services/results.service';

@Component({
  selector: 'app-results-list',
  templateUrl: './results-list.component.html',
  styleUrls: ['./results-list.component.scss']
})
export class ResultsListComponent implements OnInit {

  resultados: Resultado[] = [];
  loading = false;
  error: string | null = null;

  constructor(private resultsService: ResultsService) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.error = null;

    this.resultsService.getAll().subscribe({
      next: (data: Resultado[]) => {
        this.resultados = data ?? [];
        this.loading = false;
      },
      error: (err: unknown) => {
        console.error(err);
        this.error = 'No se pudieron cargar los resultados (API results-service).';
        this.loading = false;
      }
    });
  }
}

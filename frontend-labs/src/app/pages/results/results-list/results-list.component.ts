import { Component, OnInit } from '@angular/core';
import { ResultsService } from 'src/app/services/results.service';
import { Resultado } from 'src/app/models/resultado';

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
    this.cargarResultados();
  }

  cargarResultados(): void {
    this.loading = true;
    this.error = null;

    this.resultsService.getAll().subscribe({
      next: (data) => {
        this.resultados = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'No se pudieron cargar los resultados. Intenta más tarde.';
        this.loading = false;
      }
    });
  }
}

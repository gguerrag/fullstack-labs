import { Injectable } from '@angular/core';
import { AnalysisResult } from '../models/result.model';

@Injectable({
  providedIn: 'root'
})
export class ResultsService {

  private results: AnalysisResult[] = [
    {
      id: 1,
      userId: 2, // Paciente demo
      labId: 1,
      tipo: 'Hemograma',
      fecha: '2025-11-15',
      resultado: 'Dentro de rangos normales.',
      estado: 'Completado'
    },
    {
      id: 2,
      userId: 2,
      labId: 2,
      tipo: 'Perfil Lipídico',
      fecha: '2025-11-18',
      resultado: 'Colesterol total levemente elevado.',
      estado: 'Completado'
    },
    {
      id: 3,
      userId: 2,
      labId: 1,
      tipo: 'Glucosa en ayunas',
      fecha: '2025-11-20',
      resultado: 'Resultado pendiente de validación.',
      estado: 'Pendiente'
    }
  ];

  getResultsByUser(userId: number): AnalysisResult[] {
    return this.results.filter(r => r.userId === userId);
  }

  getAll(): AnalysisResult[] {
    return this.results;
  }
}

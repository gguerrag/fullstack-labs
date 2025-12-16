import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AnalysisResult } from '../models/result.model';

@Injectable({
  providedIn: 'root'
})
export class ResultsService {

  private results: AnalysisResult[] = [
    {
      id: 1,
      userId: 2,
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


  create(payload: Partial<AnalysisResult>): Observable<AnalysisResult> {
    const nextId = this.results.length ? Math.max(...this.results.map(r => r.id ?? 0)) + 1 : 1;

    const created: AnalysisResult = {
      id: nextId,
      userId: Number((payload as any).userId ?? (payload as any).usuarioId ?? 0),
      labId: Number((payload as any).labId ?? (payload as any).laboratorioId ?? 0),
      tipo: String((payload as any).tipo ?? (payload as any).tipoExamen ?? '').trim(),
      fecha: String((payload as any).fecha ?? (payload as any).fechaResultado ?? new Date().toISOString().slice(0, 10)),
      resultado: String((payload as any).resultado ?? (payload as any).valorResultado ?? '').trim(),
      estado: ((payload as any).estado === 'Completado' ? 'Completado' : 'Pendiente')

    };

    this.results = [created, ...this.results];
    return of(created);
  }
}

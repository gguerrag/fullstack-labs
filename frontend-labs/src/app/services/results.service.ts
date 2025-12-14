import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Resultado {
  id?: number;
  usuarioId: number;
  laboratorioId: number;
  tipoExamen: string;
  valorResultado: string;
  unidad?: string;
  estado?: string;
  fechaResultado?: string; // ISO string
}

@Injectable({ providedIn: 'root' })
export class ResultsService {
  private baseUrl = 'http://localhost:8083/api/results';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Resultado[]> {
    return this.http.get<Resultado[]>(this.baseUrl);
  }

  create(payload: Resultado): Observable<Resultado> {
    return this.http.post<Resultado>(this.baseUrl, payload);
  }

  // opcional (por si luego completas CRUD)
  getById(id: number): Observable<Resultado> {
    return this.http.get<Resultado>(`${this.baseUrl}/${id}`);
  }

  update(id: number, payload: Resultado): Observable<Resultado> {
    return this.http.put<Resultado>(`${this.baseUrl}/${id}`, payload);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

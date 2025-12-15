import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

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

@Injectable({
  providedIn: 'root'
})
export class ResultsService {
  private readonly baseUrl = `${environment.apiResultsUrl}/api/results`;

  
  constructor(private http: HttpClient) {}

  /** GET /api/results */
  getAll(): Observable<Resultado[]> {
    return this.http.get<Resultado[]>(this.baseUrl);
  }

  /** GET /api/results/{id} */
  getById(id: number): Observable<Resultado> {
    return this.http.get<Resultado>(`${this.baseUrl}/${id}`);
  }

  /** GET /api/results/user/{usuarioId} */
  getByUser(usuarioId: number): Observable<Resultado[]> {
    return this.http.get<Resultado[]>(`${this.baseUrl}/user/${usuarioId}`);
  }

  /** GET /api/results/lab/{laboratorioId} */
  getByLab(laboratorioId: number): Observable<Resultado[]> {
    return this.http.get<Resultado[]>(`${this.baseUrl}/lab/${laboratorioId}`);
  }

  /** POST /api/results */
  create(payload: Resultado): Observable<Resultado> {
    return this.http.post<Resultado>(this.baseUrl, payload);
  }

  /** PUT /api/results/{id} */
  update(id: number, payload: Resultado): Observable<Resultado> {
    return this.http.put<Resultado>(`${this.baseUrl}/${id}`, payload);
  }

  /** DELETE /api/results/{id} */
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

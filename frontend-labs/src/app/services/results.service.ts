import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Resultado } from '../models/resultado';

@Injectable({
  providedIn: 'root',
})
export class ResultsService {
  private readonly baseUrl = 'http://localhost:8083/api/results';

  constructor(private http: HttpClient) {}

  // Obtener todos los resultados (puedes filtrar por usuario más adelante)
  getResultados(): Observable<Resultado[]> {
    return this.http.get<Resultado[]>(this.baseUrl);
  }

  // Obtener por id
  getResultadoById(id: number): Observable<Resultado> {
    return this.http.get<Resultado>(`${this.baseUrl}/${id}`);
  }

  // Crear
  crearResultado(resultado: Resultado): Observable<Resultado> {
    return this.http.post<Resultado>(this.baseUrl, resultado);
  }

  // Actualizar
  actualizarResultado(id: number, resultado: Resultado): Observable<Resultado> {
    return this.http.put<Resultado>(`${this.baseUrl}/${id}`, resultado);
  }

  // Eliminar
  eliminarResultado(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

import { Component, OnInit } from '@angular/core';

type StoredUser = {
  nombre?: string;
  name?: string;
  email?: string;
  run?: string;
};

@Component({
  selector: 'app-patient-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  user: { nombre: string } | null = null;

  totalResultados = 0;
  totalCompletados = 0;
  totalPendientes = 0;

  ngOnInit(): void {
    this.loadUserFromStorage();
    this.loadCountersFromStorage();
  }

  private loadUserFromStorage(): void {
    // Ajusta la key si tu app guarda otro nombre (ej: 'currentUser', 'auth_user', etc.)
    const raw = localStorage.getItem('user') || localStorage.getItem('currentUser');

    if (!raw) {
      this.user = null;
      return;
    }

    try {
      const parsed = JSON.parse(raw) as StoredUser;

      // Rescatamos nombre desde distintas llaves típicas
      const nombre = (parsed.nombre || parsed.name || parsed.email || 'Paciente').toString();

      this.user = { nombre };
    } catch {
      this.user = { nombre: 'Paciente' };
    }
  }

  private loadCountersFromStorage(): void {
    // Si después quieres traer estos datos desde tu API, aquí reemplazas por service calls.
    const raw = localStorage.getItem('patient_dashboard_counts');

    if (!raw) {
      this.totalResultados = 0;
      this.totalCompletados = 0;
      this.totalPendientes = 0;
      return;
    }

    try {
      const parsed = JSON.parse(raw) as Partial<{
        totalResultados: number;
        totalCompletados: number;
        totalPendientes: number;
      }>;

      this.totalResultados = Number(parsed.totalResultados ?? 0);
      this.totalCompletados = Number(parsed.totalCompletados ?? 0);
      this.totalPendientes = Number(parsed.totalPendientes ?? 0);
    } catch {
      this.totalResultados = 0;
      this.totalCompletados = 0;
      this.totalPendientes = 0;
    }
  }
}

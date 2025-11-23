import { Injectable } from '@angular/core';
import { Laboratory } from '../models/laboratory.model';

@Injectable({
  providedIn: 'root'
})
export class LabsService {

  private labs: Laboratory[] = [
    {
      id: 1,
      nombre: 'BioLab Central',
      direccion: 'Av. Principal 123, Santiago',
      telefono: '+56 2 1234 5678',
      estado: 'Activo'
    },
    {
      id: 2,
      nombre: 'MedTest Clínica Norte',
      direccion: 'Calle Norte 456, Santiago',
      telefono: '+56 2 2345 6789',
      estado: 'Activo'
    },
    {
      id: 3,
      nombre: 'CliniLab Sur',
      direccion: 'Av. Sur 789, Santiago',
      telefono: '+56 2 3456 7890',
      estado: 'Inactivo'
    }
  ];

  getLabs(): Laboratory[] {
    return this.labs;
  }

  addLab(lab: Omit<Laboratory, 'id'>): void {
    const nuevo: Laboratory = {
      ...lab,
      id: this.labs.length + 1
    };
    this.labs.push(nuevo);
  }

  toggleEstado(id: number): void {
    const lab = this.labs.find(l => l.id === id);
    if (lab) {
      lab.estado = lab.estado === 'Activo' ? 'Inactivo' : 'Activo';
    }
  }
}

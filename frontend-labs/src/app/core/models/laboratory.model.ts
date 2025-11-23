export interface Laboratory {
  id: number;
  nombre: string;
  direccion: string;
  telefono: string;
  estado: 'Activo' | 'Inactivo';
}

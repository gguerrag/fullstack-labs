export interface Resultado {
  id?: number;
  usuarioId: number;
  laboratorioId: number;
  tipoExamen: string;
  valorResultado: string;
  unidad?: string;
  estado?: string;
  fechaResultado: string; // ISO string
}
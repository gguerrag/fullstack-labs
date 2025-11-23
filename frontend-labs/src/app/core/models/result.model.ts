export interface AnalysisResult {
  id: number;
  userId: number;
  labId: number;
  tipo: string;
  fecha: string; // ISO string o texto corto
  resultado: string;
  estado: 'Pendiente' | 'Completado';
}

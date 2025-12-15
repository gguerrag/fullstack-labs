import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ResultsService } from './results.service';

// Si tu interfaz está en otra ruta, cambia este import.
export interface Resultado {
  id?: number;
  usuarioId: number;
  laboratorioId: number;
  tipoExamen: string;
  valorResultado: string;
  unidad?: string;
  estado?: string;
  fechaResultado?: string; // o Date según tu modelo
}

describe('ResultsService', () => {
  let service: ResultsService;
  let httpMock: HttpTestingController;

  // Mock completo
  const mockResultado: Resultado = {
    id: 1,
    usuarioId: 1,
    laboratorioId: 1,
    tipoExamen: 'Glucosa',
    valorResultado: '105',
    unidad: 'mg/dL',
    estado: 'LIBERADO',
    fechaResultado: '2025-12-11T12:00:00',
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ResultsService],
    });

    service = TestBed.inject(ResultsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getAll should GET resultados', () => {
    service.getAll().subscribe((res) => {
      expect(res.length).toBe(1);
      expect(res[0].tipoExamen).toBe('Glucosa');
    });

    const req = httpMock.expectOne((r) => r.method === 'GET' && r.url.includes('/api/results'));
    req.flush([mockResultado]);
  });

  it('getById should GET resultado by id', () => {
    service.getById(1).subscribe((res) => {
      expect(res.id).toBe(1);
      expect(res.usuarioId).toBe(1);
    });

    const req = httpMock.expectOne((r) => r.method === 'GET' && r.url.includes('/api/results/1'));
    req.flush(mockResultado);
  });

  it('getByUser should GET resultados by usuarioId', () => {
    service.getByUser(1).subscribe((res) => {
      expect(res.length).toBeGreaterThan(0);
      expect(res[0].usuarioId).toBe(1);
    });

    const req = httpMock.expectOne((r) => r.method === 'GET' && r.url.includes('/api/results/user/1'));
    req.flush([mockResultado]);
  });

  it('getByLab should GET resultados by laboratorioId', () => {
    service.getByLab(1).subscribe((res) => {
      expect(res.length).toBeGreaterThan(0);
      expect(res[0].laboratorioId).toBe(1);
    });

    const req = httpMock.expectOne((r) => r.method === 'GET' && r.url.includes('/api/results/lab/1'));
    req.flush([mockResultado]);
  });

  it('create should POST resultado', () => {
    const payload: Resultado = {
      usuarioId: 2,
      laboratorioId: 3,
      tipoExamen: 'Hemograma',
      valorResultado: '13',
      unidad: 'g/dL',
      estado: 'EN PROCESO',
      fechaResultado: '2025-12-14T01:05:00',
    };

    service.create(payload).subscribe((res) => {
      expect(res.id).toBe(99);
      expect(res.tipoExamen).toBe('Hemograma');
    });

    const req = httpMock.expectOne((r) => r.method === 'POST' && r.url.includes('/api/results'));
    expect(req.request.body).toEqual(payload);
    req.flush({ ...payload, id: 99 });
  });

  it('update should PUT resultado', () => {
    const payload: Resultado = { ...mockResultado, tipoExamen: 'Glucosa (edit)' };

    service.update(1, payload).subscribe((res) => {
      expect(res.tipoExamen).toBe('Glucosa (edit)');
    });

    const req = httpMock.expectOne((r) => r.method === 'PUT' && r.url.includes('/api/results/1'));
    req.flush(payload);
  });

  it('delete should DELETE resultado', () => {
    service.delete(1).subscribe((res) => {
      // usualmente viene vacío
      expect(res).toBeTruthy();
    });

    const req = httpMock.expectOne((r) => r.method === 'DELETE' && r.url.includes('/api/results/1'));
    req.flush({});
  });

  // Rama de error: sube branches
  it('create should propagate error (500)', () => {
    const payload: Resultado = {
      usuarioId: 1,
      laboratorioId: 1,
      tipoExamen: 'X',
      valorResultado: 'Y',
    };

    service.create(payload).subscribe({
      next: () => fail('should not succeed'),
      error: (err) => {
        expect(err.status).toBe(500);
      },
    });

    const req = httpMock.expectOne((r) => r.method === 'POST' && r.url.includes('/api/results'));
    req.flush({ message: 'fail' }, { status: 500, statusText: 'Server Error' });
  });
});

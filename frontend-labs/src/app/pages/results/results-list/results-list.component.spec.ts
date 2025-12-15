import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { ResultsListComponent } from './results-list.component';
import { ResultsService } from '../../../services/results.service';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('ResultsListComponent', () => {
  let component: ResultsListComponent;
  let fixture: ComponentFixture<ResultsListComponent>;
  let serviceSpy: jasmine.SpyObj<ResultsService>;

  const mockResultado = {
    id: 1,
    usuarioId: 1,
    laboratorioId: 1,
    tipoExamen: 'Glucosa',
    valorResultado: '105',
    unidad: 'mg/dL',
    estado: 'LIBERADO',
    fechaResultado: '2025-12-11T12:00:00'
  };

  beforeEach(async () => {
    serviceSpy = jasmine.createSpyObj('ResultsService', ['getAll']);

    await TestBed.configureTestingModule({
      declarations: [ResultsListComponent],
      providers: [{ provide: ResultsService, useValue: serviceSpy }],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();

    fixture = TestBed.createComponent(ResultsListComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    serviceSpy.getAll.and.returnValue(of([] as any));
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  it('should load results', () => {
    serviceSpy.getAll.and.returnValue(of([mockResultado] as any));
    fixture.detectChanges();

    expect(component.resultados.length).toBe(1);
    expect(component.resultados[0].tipoExamen).toBe('Glucosa');
  });
});

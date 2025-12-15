import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { of, throwError } from 'rxjs';
import { ResultsFormComponent } from './results-form.component';
import { ResultsService } from '../../../services/results.service';
import { Router } from '@angular/router';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('ResultsFormComponent', () => {
  let component: ResultsFormComponent;
  let fixture: ComponentFixture<ResultsFormComponent>;
  let serviceSpy: jasmine.SpyObj<ResultsService>;
  let routerSpy: jasmine.SpyObj<Router>;

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
    serviceSpy = jasmine.createSpyObj('ResultsService', ['create']);
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [ResultsFormComponent],
      imports: [ReactiveFormsModule],
      providers: [
        { provide: ResultsService, useValue: serviceSpy },
        { provide: Router, useValue: routerSpy },
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();

    fixture = TestBed.createComponent(ResultsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should submit form successfully', () => {
    component.form.setValue({
      usuarioId: 1,
      laboratorioId: 1,
      tipoExamen: 'Test',
      valorResultado: '10',
      unidad: 'mg',
      estado: 'PENDIENTE',
      fechaResultado: '2025-12-14T10:00',
    });

    serviceSpy.create.and.returnValue(of(mockResultado as any));

    component.onSubmit();

    expect(serviceSpy.create).toHaveBeenCalled();
    expect(routerSpy.navigate).toHaveBeenCalled();
  });

it('should handle error on submit (should not navigate)', () => {
  component.form.setValue({
    usuarioId: 1,
    laboratorioId: 1,
    tipoExamen: 'Test',
    valorResultado: '10',
    unidad: 'mg',
    estado: 'PENDIENTE',
    fechaResultado: '2025-12-14T10:00',
  });

  serviceSpy.create.and.returnValue(
    throwError(() => ({ status: 500 }))
  );

  component.onSubmit();

  expect(serviceSpy.create).toHaveBeenCalled();
  expect(routerSpy.navigate).not.toHaveBeenCalled();
});

});

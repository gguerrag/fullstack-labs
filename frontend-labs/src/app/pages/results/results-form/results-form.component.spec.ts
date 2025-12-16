import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ResultsFormComponent } from './results-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { NO_ERRORS_SCHEMA } from '@angular/core';

import { ResultsService } from '../../../core/services/results.service';

describe('ResultsFormComponent (coverage)', () => {
  let component: ResultsFormComponent;
  let fixture: ComponentFixture<ResultsFormComponent>;

  let routerSpy: jasmine.SpyObj<Router>;
  let resultsServiceSpy: jasmine.SpyObj<ResultsService>;

  beforeEach(async () => {
    routerSpy = jasmine.createSpyObj('Router', ['navigate']);
    resultsServiceSpy = jasmine.createSpyObj<ResultsService>('ResultsService', ['create', 'getAll', 'getResultsByUser']);

    await TestBed.configureTestingModule({
      declarations: [ResultsFormComponent],
      imports: [ReactiveFormsModule],
      providers: [
        { provide: Router, useValue: routerSpy },
        { provide: ResultsService, useValue: resultsServiceSpy }
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(ResultsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(component.form).toBeTruthy();
  });

  it('invalid submit should set errorMessage and not call create', () => {
    component.form.patchValue({
      usuarioId: null,
      laboratorioId: null,
      tipoExamen: '',
      valorResultado: ''
    });

    component.onSubmit();

    expect(component.submitted).toBeTrue();
    expect(component.errorMessage).toContain('Formulario inválido');
    expect(resultsServiceSpy.create).not.toHaveBeenCalled();
  });

  it('valid submit should call create and navigate', () => {
    spyOn(window, 'alert');
    resultsServiceSpy.create.and.returnValue(of({} as any));

    component.form.patchValue({
      usuarioId: 1,
      laboratorioId: 2,
      tipoExamen: 'Sangre',
      valorResultado: 'OK',
      estado: 'EN PROCESO'
    });

    component.onSubmit();

    expect(resultsServiceSpy.create).toHaveBeenCalled();
    expect(window.alert).toHaveBeenCalled();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['admin', 'resultados']);
    expect(component.successMessage).toContain('Resultado registrado');
  });

  it('service error should set errorMessage', () => {
    resultsServiceSpy.create.and.returnValue(throwError(() => new Error('fail')));

    component.form.patchValue({
      usuarioId: 1,
      laboratorioId: 2,
      tipoExamen: 'Sangre',
      valorResultado: 'OK'
    });

    component.onSubmit();

    expect(resultsServiceSpy.create).toHaveBeenCalled();
    expect(component.errorMessage).toContain('No se pudo');
  });

  it('resetForm should reset values and flags', () => {
    component.submitted = true;
    component.errorMessage = 'x';
    component.successMessage = 'y';

    component.form.patchValue({
      usuarioId: 9,
      laboratorioId: 9,
      tipoExamen: 'AA',
      valorResultado: 'BB',
      estado: 'FINALIZADO'
    });

    component.resetForm();

    expect(component.submitted).toBeFalse();
    expect(component.errorMessage).toBeNull();
    expect(component.successMessage).toBeNull();
    expect(component.form.value.estado).toBe('EN PROCESO');
  });
});

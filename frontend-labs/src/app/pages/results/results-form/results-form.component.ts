import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ResultsService } from '../../../core/services/results.service';
import { AnalysisResult } from '../../../core/models/result.model';

@Component({
  selector: 'app-results-form',
  templateUrl: './results-form.component.html',
  styleUrls: ['./results-form.component.scss']
})
export class ResultsFormComponent implements OnInit {
  form!: FormGroup;
  submitted = false;

  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private resultsService: ResultsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      usuarioId: [null, [Validators.required, Validators.min(1)]],
      laboratorioId: [null, [Validators.required, Validators.min(1)]],
      tipoExamen: ['', [Validators.required, Validators.minLength(2)]],
      valorResultado: ['', [Validators.required, Validators.minLength(1)]],
      unidad: [''],
      estado: ['EN PROCESO'],
      fechaResultado: ['']
    });
  }

  get f() {
    return this.form.controls;
  }

  resetForm(): void {
    this.submitted = false;
    this.errorMessage = null;
    this.successMessage = null;

    this.form.reset({
      usuarioId: null,
      laboratorioId: null,
      tipoExamen: '',
      valorResultado: '',
      unidad: '',
      estado: 'EN PROCESO',
      fechaResultado: ''
    });
  }

  onSubmit(): void {
    this.submitted = true;
    this.errorMessage = null;
    this.successMessage = null;

    if (this.form.invalid) {
      this.errorMessage = 'Formulario inválido. Revisa los campos obligatorios.';
      return;
    }

    // 👇 lo mapeo a AnalysisResult (tu modelo real)
    const payload: Partial<AnalysisResult> = {
      userId: Number(this.form.value.usuarioId),
      labId: Number(this.form.value.laboratorioId),
      tipo: String(this.form.value.tipoExamen).trim(),
      resultado: String(this.form.value.valorResultado).trim(),
      estado: (this.form.value.estado === 'Completado' ? 'Completado' : 'Pendiente'),

      fecha: this.form.value.fechaResultado
        ? String(this.form.value.fechaResultado)
        : new Date().toISOString().slice(0, 10)
    };

    this.resultsService.create(payload).subscribe({
      next: () => {
        this.successMessage = 'Resultado registrado correctamente.';
        alert(this.successMessage);
        this.router.navigate(['admin', 'resultados']);
      },
      error: (err: any) => {
        console.error(err);
        this.errorMessage = 'No se pudo registrar el resultado.';
      }
    });
  }
}

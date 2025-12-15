import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ResultsService, Resultado } from '../../../services/results.service';

@Component({
  selector: 'app-results-form',
  templateUrl: './results-form.component.html',
  styleUrls: ['./results-form.component.scss']
})
export class ResultsFormComponent implements OnInit {
  form!: FormGroup;
  submitted = false;

  // 👇 claves para tests y UX
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

  // ayuda para template: f['usuarioId'] (evita TS4111)
  get f() {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    this.errorMessage = null;
    this.successMessage = null;

    if (this.form.invalid) {
      this.errorMessage = 'Formulario inválido. Revisa los campos obligatorios.';
      return;
    }

    const payload: Resultado = {
      usuarioId: Number(this.form.value.usuarioId),
      laboratorioId: Number(this.form.value.laboratorioId),
      tipoExamen: String(this.form.value.tipoExamen).trim(),
      valorResultado: String(this.form.value.valorResultado).trim(),
      unidad: this.form.value.unidad ? String(this.form.value.unidad).trim() : undefined,
      estado: this.form.value.estado ? String(this.form.value.estado).trim() : undefined,
      fechaResultado: this.form.value.fechaResultado
        ? String(this.form.value.fechaResultado)
        : undefined
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

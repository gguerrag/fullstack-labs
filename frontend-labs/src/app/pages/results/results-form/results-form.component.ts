import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AnalysisResult, Estado } from '../../../core/models/result.model';
import { ResultsService } from '../../../core/services/results.service';

@Component({
  selector: 'app-results-form',
  templateUrl: './results-form.component.html',
})
export class ResultsFormComponent {
  submitted = false;
  saving = false;
  error: string | null = null;

  form = this.fb.group({
    // estos nombres pueden quedar como los tienes en el HTML:
    usuarioId: ['', [Validators.required]],
    laboratorioId: ['', [Validators.required]],
    tipoExamen: ['', [Validators.required, Validators.minLength(2)]],
    valorResultado: ['', [Validators.required, Validators.minLength(1)]],
    fechaResultado: ['', [Validators.required]],
    estado: ['Pendiente' as Estado, [Validators.required]],
  });

  constructor(
    private fb: FormBuilder,
    private resultsService: ResultsService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.submitted = true;
    this.error = null;

    if (this.form.invalid) return;

    this.saving = true;

    const estadoForm = (this.form.get('estado')!.value ?? 'Pendiente') as Estado;

    // ✅ Mapeo a lo que realmente existe en AnalysisResult
    const payload: Partial<AnalysisResult> = {
      userId: Number(this.form.get('usuarioId')!.value),
      labId: Number(this.form.get('laboratorioId')!.value),
      tipo: String(this.form.get('tipoExamen')!.value ?? ''),
      resultado: String(this.form.get('valorResultado')!.value ?? ''),
      fecha: String(this.form.get('fechaResultado')!.value ?? ''),
      estado: estadoForm,
    };

    this.resultsService.create(payload).subscribe({
      next: () => {
        this.saving = false;
        // ajusta la ruta si tu app usa otra
        this.router.navigate(['/results']);
      },
      error: (err) => {
        this.saving = false;
        this.error = err?.error?.message ?? 'Error guardando el resultado';
      },
    });
  }

  // opcional: para usar en HTML si quieres mostrar errores por control
  get f() {
    return this.form.controls;
  }
}

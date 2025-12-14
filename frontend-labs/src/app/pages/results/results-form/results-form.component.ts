import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ResultsService } from 'src/app/services/results.service';

@Component({
  selector: 'app-results-form',
  templateUrl: './results-form.component.html',
  styleUrls: ['./results-form.component.scss']
})
export class ResultsFormComponent {

  form: FormGroup;
  submitted = false;
  saving = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private resultsService: ResultsService,
    private router: Router
  ) {
    this.form = this.fb.group({
      usuarioId: [1, [Validators.required, Validators.min(1)]],
      laboratorioId: [1, [Validators.required, Validators.min(1)]],
      tipoExamen: ['', [Validators.required, Validators.maxLength(100)]],
      valorResultado: ['', [Validators.required, Validators.maxLength(100)]],
      unidad: ['mg/dL', [Validators.maxLength(50)]],
      estado: ['LIBERADO', [Validators.maxLength(30)]],
      fechaResultado: [new Date().toISOString().slice(0, 16), [Validators.required]]
    });
  }

  get f() {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    this.error = null;

    if (this.form.invalid) {
      return;
    }

    this.saving = true;

    const payload = {
      ...this.form.value,
      // agregamos ":00" a la fecha para tener segundos y que sea ISO completa
      fechaResultado: this.form.value.fechaResultado + ':00'
    };

    this.resultsService.create(payload).subscribe({
      next: () => {
        this.saving = false;
        alert('Resultado registrado correctamente.');
        this.router.navigate(['/admin/resultados']);
      },
      error: (err: unknown) => {
        console.error(err);
      }
    });
  }
}

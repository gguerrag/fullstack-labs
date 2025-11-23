import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-recover',
  templateUrl: './recover.component.html'
})
export class RecoverComponent {

  recoverForm: FormGroup;
  submitted = false;
  success = false;

  constructor(private fb: FormBuilder) {
    this.recoverForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  get f() {
    return this.recoverForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.recoverForm.invalid) return;

    // Simulación
    this.success = true;
  }
}

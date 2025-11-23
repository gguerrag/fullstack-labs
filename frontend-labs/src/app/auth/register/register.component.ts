import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  registerForm: FormGroup;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.registerForm = this.fb.group({
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [
        Validators.required,
        Validators.minLength(8),   // longitud mínima
        Validators.maxLength(20),  // longitud máxima
        Validators.pattern(/^(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).+$/) // mayúscula, número y caracter especial
      ]],
      rol: ['PATIENT', [Validators.required]]
    });
  }

  get f() { return this.registerForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }

    const { nombre, apellido, email, password, rol } = this.registerForm.value;
    this.authService.addUser({ nombre, apellido, email, password, rol });
    alert('Usuario registrado (simulado).');

    this.registerForm.reset({ rol: 'PATIENT' });
    this.submitted = false;
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { User } from 'src/app/core/models/user.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {

  profileForm: FormGroup;
  submitted = false;
  user: User | null = null;
  success = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.profileForm = this.fb.group({
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      email: [{ value: '', disabled: true }],
      rol: [{ value: '', disabled: true }]
    });
  }

  get f() { return this.profileForm.controls; }

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();
    if (this.user) {
      this.profileForm.patchValue({
        nombre: this.user.nombre,
        apellido: this.user.apellido,
        email: this.user.email,
        rol: this.user.rol
      });
    }
  }

  onSubmit(): void {
    this.submitted = true;
    this.success = false;
    if (this.profileForm.invalid || !this.user) return;

    this.user.nombre = this.profileForm.value.nombre;
    this.user.apellido = this.profileForm.value.apellido;
    this.success = true;
  }
}

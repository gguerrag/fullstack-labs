import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  let authServiceSpy: jasmine.SpyObj<AuthService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    authServiceSpy = jasmine.createSpyObj<AuthService>('AuthService', ['login', 'getRole']);
    routerSpy = jasmine.createSpyObj<Router>('Router', ['navigate']);

    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [LoginComponent],
      providers: [
        { provide: AuthService, useValue: authServiceSpy },
        { provide: Router, useValue: routerSpy },
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(component.loginForm).toBeTruthy();
  });

  it('should not submit when form is invalid', () => {
    component.loginForm.patchValue({ email: '', password: '' });
    authServiceSpy.login.and.returnValue(false);

    component.onSubmit();

    expect(authServiceSpy.login).not.toHaveBeenCalled();
    expect(routerSpy.navigate).not.toHaveBeenCalled();
  });

  it('should login and navigate to /admin when role is ADMIN', () => {
    component.loginForm.patchValue({ email: 'admin@labs.cl', password: 'admin123' });

    authServiceSpy.login.and.returnValue(true);
    authServiceSpy.getRole.and.returnValue('ADMIN');

    component.onSubmit();

    expect(authServiceSpy.login).toHaveBeenCalled();
    expect(authServiceSpy.getRole).toHaveBeenCalled();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/admin']);
  });

  it('should login and navigate to /patient when role is PATIENT', () => {
    component.loginForm.patchValue({ email: 'patient@labs.cl', password: 'patient123' });

    authServiceSpy.login.and.returnValue(true);
    authServiceSpy.getRole.and.returnValue('PATIENT');

    component.onSubmit();

    expect(authServiceSpy.login).toHaveBeenCalled();
    expect(authServiceSpy.getRole).toHaveBeenCalled();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/patient']);
  });

  it('should show error when login fails', () => {
    component.loginForm.patchValue({ email: 'x@x.com', password: 'bad' });

    authServiceSpy.login.and.returnValue(false);

    component.onSubmit();

    expect(component.errorMessage).toBeTruthy();
    expect(routerSpy.navigate).not.toHaveBeenCalled();
  });
});

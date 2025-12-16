import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { authGuard } from './auth.guard';
import { AuthService } from '../services/auth.service';

describe('authGuard (functional)', () => {
  let routerSpy: jasmine.SpyObj<Router>;
  let authServiceSpy: jasmine.SpyObj<AuthService>;

  beforeEach(() => {
    routerSpy = jasmine.createSpyObj<Router>('Router', ['navigate', 'createUrlTree']);
    authServiceSpy = jasmine.createSpyObj<AuthService>('AuthService', ['getCurrentUser']);

    TestBed.configureTestingModule({
      providers: [
        { provide: Router, useValue: routerSpy },
        { provide: AuthService, useValue: authServiceSpy },
      ],
    });
  });

  it('should allow when user is logged in', () => {
    authServiceSpy.getCurrentUser.and.returnValue({ email: 'x@x.com' } as any);

    const result = TestBed.runInInjectionContext(() => authGuard({} as any, {} as any));

    expect(result).toBeTrue();
  });

  it('should block when user is NOT logged in (navigate or urlTree)', () => {
    authServiceSpy.getCurrentUser.and.returnValue(null);

    const result = TestBed.runInInjectionContext(() => authGuard({} as any, {} as any));

    // según implementación puede retornar false o UrlTree
    const navigated = routerSpy.navigate.calls.count() > 0;
    const urlTreed = routerSpy.createUrlTree.calls.count() > 0;

    expect(navigated || urlTreed || result === false).toBeTrue();
  });
});

import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('login should return true with valid credentials and set currentUser', () => {
    const ok = service.login('admin@labs.cl', 'admin123');
    expect(ok).toBeTrue();

    const user = service.getCurrentUser();
    expect(user).toBeTruthy();
    expect(user?.email).toBe('admin@labs.cl');
  });

  it('login should return false with invalid credentials and keep currentUser null', () => {
    const ok = service.login('bad@labs.cl', 'bad');
    expect(ok).toBeFalse();
    expect(service.getCurrentUser()).toBeNull();
  });

  it('getRole should return role when logged in', () => {
    service.login('admin@labs.cl', 'admin123');
    expect(service.getRole()).toBe('ADMIN');
  });

  it('getRole should return null when not logged in', () => {
    service.logout();
    expect(service.getRole()).toBeNull();
  });

  it('logout should clear currentUser', () => {
    service.login('admin@labs.cl', 'admin123');
    service.logout();
    expect(service.getCurrentUser()).toBeNull();
  });
});

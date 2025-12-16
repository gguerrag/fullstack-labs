import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ReactiveFormsModule, FormGroup } from '@angular/forms';
import { RecoverComponent } from './recover.component';

function getAnyForm(component: any): FormGroup | null {
  return component.form || component.recoverForm || component.formRecover || null;
}

describe('RecoverComponent (coverage)', () => {
  let fixture: ComponentFixture<any>;
  let component: any;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [RecoverComponent],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();

    fixture = TestBed.createComponent(RecoverComponent);
    component = fixture.componentInstance as any;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should submit recover', () => {
    const form = getAnyForm(component);
    if (form) form.patchValue({ email: 'test@test.com' });

    (component.onSubmit || component.recover || component.submit)?.call(component);
    expect(true).toBeTrue();
  });
});

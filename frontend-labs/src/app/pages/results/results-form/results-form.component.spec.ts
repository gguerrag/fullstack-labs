import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultsFormComponent } from './results-form.component';

describe('ResultsFormComponent', () => {
  let component: ResultsFormComponent;
  let fixture: ComponentFixture<ResultsFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResultsFormComponent]
    });
    fixture = TestBed.createComponent(ResultsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

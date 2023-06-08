import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PevaluationComponent } from './pevaluation.component';

describe('PevaluationComponent', () => {
  let component: PevaluationComponent;
  let fixture: ComponentFixture<PevaluationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PevaluationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PevaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

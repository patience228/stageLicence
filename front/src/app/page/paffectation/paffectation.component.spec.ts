import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaffectationComponent } from './paffectation.component';

describe('PaffectationComponent', () => {
  let component: PaffectationComponent;
  let fixture: ComponentFixture<PaffectationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaffectationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaffectationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

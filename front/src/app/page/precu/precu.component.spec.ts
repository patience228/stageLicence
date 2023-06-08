import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrecuComponent } from './precu.component';

describe('PrecuComponent', () => {
  let component: PrecuComponent;
  let fixture: ComponentFixture<PrecuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrecuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PrecuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

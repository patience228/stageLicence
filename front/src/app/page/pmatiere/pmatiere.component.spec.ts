import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PmatiereComponent } from './pmatiere.component';

describe('PmatiereComponent', () => {
  let component: PmatiereComponent;
  let fixture: ComponentFixture<PmatiereComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PmatiereComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PmatiereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

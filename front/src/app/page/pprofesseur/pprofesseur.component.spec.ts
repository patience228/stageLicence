import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PprofesseurComponent } from './pprofesseur.component';

describe('PprofesseurComponent', () => {
  let component: PprofesseurComponent;
  let fixture: ComponentFixture<PprofesseurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PprofesseurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PprofesseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

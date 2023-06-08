import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PanneescolaireComponent } from './panneescolaire.component';

describe('PanneescolaireComponent', () => {
  let component: PanneescolaireComponent;
  let fixture: ComponentFixture<PanneescolaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PanneescolaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PanneescolaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

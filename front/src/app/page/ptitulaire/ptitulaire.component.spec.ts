import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PtitulaireComponent } from './ptitulaire.component';

describe('PtitulaireComponent', () => {
  let component: PtitulaireComponent;
  let fixture: ComponentFixture<PtitulaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PtitulaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PtitulaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

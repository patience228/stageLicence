import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FanneeScolaireComponent } from './fannee-scolaire.component';

describe('FanneeScolaireComponent', () => {
  let component: FanneeScolaireComponent;
  let fixture: ComponentFixture<FanneeScolaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FanneeScolaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FanneeScolaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

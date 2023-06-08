import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PpreinscriptionComponent } from './ppreinscription.component';

describe('PpreinscriptionComponent', () => {
  let component: PpreinscriptionComponent;
  let fixture: ComponentFixture<PpreinscriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PpreinscriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PpreinscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

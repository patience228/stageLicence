import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PinscriptionComponent } from './pinscription.component';

describe('PinscriptionComponent', () => {
  let component: PinscriptionComponent;
  let fixture: ComponentFixture<PinscriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PinscriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PinscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

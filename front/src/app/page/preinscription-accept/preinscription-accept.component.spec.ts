import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreinscriptionAcceptComponent } from './preinscription-accept.component';

describe('PreinscriptionAcceptComponent', () => {
  let component: PreinscriptionAcceptComponent;
  let fixture: ComponentFixture<PreinscriptionAcceptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreinscriptionAcceptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreinscriptionAcceptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

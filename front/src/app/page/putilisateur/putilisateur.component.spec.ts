import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PutilisateurComponent } from './putilisateur.component';

describe('PutilisateurComponent', () => {
  let component: PutilisateurComponent;
  let fixture: ComponentFixture<PutilisateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PutilisateurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PutilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

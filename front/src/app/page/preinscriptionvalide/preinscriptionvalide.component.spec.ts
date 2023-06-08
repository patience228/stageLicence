import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreinscriptionvalideComponent } from './preinscriptionvalide.component';

describe('PreinscriptionvalideComponent', () => {
  let component: PreinscriptionvalideComponent;
  let fixture: ComponentFixture<PreinscriptionvalideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreinscriptionvalideComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreinscriptionvalideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

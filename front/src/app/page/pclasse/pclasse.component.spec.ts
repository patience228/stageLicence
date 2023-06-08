import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PclasseComponent } from './pclasse.component';

describe('PclasseComponent', () => {
  let component: PclasseComponent;
  let fixture: ComponentFixture<PclasseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PclasseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PclasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

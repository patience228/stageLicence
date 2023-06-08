import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PscolariteComponent } from './pscolarite.component';

describe('PscolariteComponent', () => {
  let component: PscolariteComponent;
  let fixture: ComponentFixture<PscolariteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PscolariteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PscolariteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

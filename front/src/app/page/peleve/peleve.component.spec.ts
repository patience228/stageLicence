import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeleveComponent } from './peleve.component';

describe('PeleveComponent', () => {
  let component: PeleveComponent;
  let fixture: ComponentFixture<PeleveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PeleveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PeleveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

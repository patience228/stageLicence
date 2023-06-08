import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecuFComponent } from './recu-f.component';

describe('RecuFComponent', () => {
  let component: RecuFComponent;
  let fixture: ComponentFixture<RecuFComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecuFComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecuFComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

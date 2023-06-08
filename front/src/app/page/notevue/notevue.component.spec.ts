import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotevueComponent } from './notevue.component';

describe('NotevueComponent', () => {
  let component: NotevueComponent;
  let fixture: ComponentFixture<NotevueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotevueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotevueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

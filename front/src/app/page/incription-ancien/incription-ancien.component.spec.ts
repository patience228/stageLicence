import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncriptionAncienComponent } from './incription-ancien.component';

describe('IncriptionAncienComponent', () => {
  let component: IncriptionAncienComponent;
  let fixture: ComponentFixture<IncriptionAncienComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IncriptionAncienComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IncriptionAncienComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

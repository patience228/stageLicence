import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsNotesComponent } from './details-notes.component';

describe('DetailsNotesComponent', () => {
  let component: DetailsNotesComponent;
  let fixture: ComponentFixture<DetailsNotesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsNotesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsNotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

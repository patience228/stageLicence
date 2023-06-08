import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PnoteComponent } from './pnote.component';

describe('PnoteComponent', () => {
  let component: PnoteComponent;
  let fixture: ComponentFixture<PnoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PnoteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PnoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

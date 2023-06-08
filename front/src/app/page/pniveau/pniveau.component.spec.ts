import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PniveauComponent } from './pniveau.component';

describe('PniveauComponent', () => {
  let component: PniveauComponent;
  let fixture: ComponentFixture<PniveauComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PniveauComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PniveauComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

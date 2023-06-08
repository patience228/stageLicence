import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PbulletinComponent } from './pbulletin.component';

describe('PbulletinComponent', () => {
  let component: PbulletinComponent;
  let fixture: ComponentFixture<PbulletinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PbulletinComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PbulletinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

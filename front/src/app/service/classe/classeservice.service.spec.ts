import { TestBed } from '@angular/core/testing';

import { ClasseserviceService } from './classeservice.service';

describe('ClasseserviceService', () => {
  let service: ClasseserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClasseserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

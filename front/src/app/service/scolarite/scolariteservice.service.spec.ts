import { TestBed } from '@angular/core/testing';

import { ScolariteserviceService } from './scolariteservice.service';

describe('ScolariteserviceService', () => {
  let service: ScolariteserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScolariteserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { EnseignementService } from './enseignement.service';

describe('EnseignementService', () => {
  let service: EnseignementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnseignementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

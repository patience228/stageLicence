import { TestBed } from '@angular/core/testing';

import { PreinscriptionserviceService } from './preinscriptionservice.service';

describe('PreinscriptionserviceService', () => {
  let service: PreinscriptionserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PreinscriptionserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { InscriptionserviceService } from './inscriptionservice.service';

describe('InscriptionserviceService', () => {
  let service: InscriptionserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InscriptionserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

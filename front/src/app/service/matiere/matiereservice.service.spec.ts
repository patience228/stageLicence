import { TestBed } from '@angular/core/testing';

import { MatiereserviceService } from './matiereservice.service';

describe('MatiereserviceService', () => {
  let service: MatiereserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatiereserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

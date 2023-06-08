import { TestBed } from '@angular/core/testing';

import { EleveserviceService } from './eleveservice.service';

describe('EleveserviceService', () => {
  let service: EleveserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EleveserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

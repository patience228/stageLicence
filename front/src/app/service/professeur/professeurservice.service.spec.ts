import { TestBed } from '@angular/core/testing';

import { ProfesseurserviceService } from './professeurservice.service';

describe('ProfesseurserviceService', () => {
  let service: ProfesseurserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfesseurserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

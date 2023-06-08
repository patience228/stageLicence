import { TestBed } from '@angular/core/testing';

import { EvaluationserviceService } from './evaluationservice.service';

describe('EvaluationserviceService', () => {
  let service: EvaluationserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EvaluationserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { TitulaireService } from './titulaire.service';

describe('TitulaireService', () => {
  let service: TitulaireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TitulaireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

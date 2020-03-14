import { TestBed } from '@angular/core/testing';

import { CoverTypeDataService } from './cover-type-data.service';

describe('CoverTypeDataService', () => {
  let service: CoverTypeDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoverTypeDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

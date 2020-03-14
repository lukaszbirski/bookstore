import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCoverTypeComponent } from './list-cover-type.component';

describe('ListCoverTypeComponent', () => {
  let component: ListCoverTypeComponent;
  let fixture: ComponentFixture<ListCoverTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListCoverTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCoverTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

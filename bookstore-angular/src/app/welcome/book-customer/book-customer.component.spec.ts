import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCustomerComponent } from './book-customer.component';

describe('BookCustomerComponent', () => {
  let component: BookCustomerComponent;
  let fixture: ComponentFixture<BookCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

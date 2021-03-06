import { Component, OnInit } from '@angular/core';
import { CoverTypeDataService } from '../../services/http/cover-type-data.service';
import { Router } from '@angular/router';

export class CoverType {
  constructor(
      public name: string,
      public books: Book[],
  ) {}
}

export class Book {
  constructor(
      public name: string,
  ) {}
}

@Component({
  selector: 'app-list-cover-type',
  templateUrl: './list-cover-type.component.html',
  styleUrls: ['./list-cover-type.component.css']
})
export class ListCoverTypeComponent implements OnInit {

  public coverTypes: CoverType[];
  public deleteError: string;

  constructor(
    private coverTypeService: CoverTypeDataService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.refreshCoverTypes();
  }

  refreshCoverTypes() {
    this.coverTypeService.retrieveAllCoverTypes().subscribe(
      response => {
        console.log(response);
        this.coverTypes = response;
      }
    );
  }

  deleteCoverType(name) {
    console.log(`delete ${name}`);
    this.coverTypeService.deleteCoverType(name).subscribe(
      response => {
        this.refreshCoverTypes();
      }, error => {
        console.log('error occured: ', error);
        this.deleteError = error.error.text;
        this.refreshCoverTypes();
      }
    );
  }

  updateCoverType(name) {
    console.log(`update ${name}`);
    this.router.navigate(['admin/covertypes', name]);
  }

  createCoverType() {
    console.log(`create coverType`);
    this.router.navigate(['admin/covertypes', '']);
  }

}

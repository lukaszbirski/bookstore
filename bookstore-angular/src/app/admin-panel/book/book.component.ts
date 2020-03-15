import { Component, OnInit } from '@angular/core';
import { CoverTypeDataService } from '../list-cover-type/cover-type-data.service';
import { CoverType } from '../list-cover-type/list-cover-type.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  public coverTypes : CoverType[]

  constructor(
    private coverTypeService : CoverTypeDataService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.refreshCoverTypes();
  }

  refreshCoverTypes(){
    this.coverTypeService.retrieveAllCoverTypes().subscribe(
      response => {
        console.log(response);
        this.coverTypes = response;
      }
    )
  }

}

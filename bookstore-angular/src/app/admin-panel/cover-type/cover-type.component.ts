import { Component, OnInit } from '@angular/core';
import { CoverType } from '../list-cover-type/list-cover-type.component';
import { CoverTypeDataService } from '../list-cover-type/cover-type-data.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cover-type',
  templateUrl: './cover-type.component.html',
  styleUrls: ['./cover-type.component.css']
})
export class CoverTypeComponent implements OnInit {

  coverType: CoverType
  name: string

  constructor(
    private coverTypeService: CoverTypeDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.name = this.route.snapshot.params['name'];
    this.coverType = new CoverType(this.name, []);
    if(this.name != '')
      this.coverTypeService.retrieveCoverType(this.name).subscribe(
        data => this.coverType = data
      )
  }

  saveCoverType(){
    if(this.name === ''){
      this.coverTypeService.createCoverType(this.name, this.coverType).subscribe(
        data => {
          this.router.navigate(['admin/covertypes'])
        }
      )
    } else{
      this.coverTypeService.updateCoverType(this.name, this.coverType).subscribe(
        data => {
          this.router.navigate(['admin/covertypes'])
        }
      )
    } 
  }

}

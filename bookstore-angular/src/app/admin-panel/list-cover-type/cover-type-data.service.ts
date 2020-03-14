import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CoverType } from './list-cover-type.component';

@Injectable({
  providedIn: 'root'
})
export class CoverTypeDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllCoverTypes(){
    return this.http.get<CoverType[]>(`http://localhost:8080/api/dto/cover_type`)
  }

  retrieveCoverType(name){
    return this.http.get<CoverType>(`http://localhost:8080/api/dto/cover_type/${name}`)
  }

  createCoverType(name, coverType){
    return this.http.post(`http://localhost:8080/api/dto/cover_type/${name}`, coverType)
  }

  updateCoverType(name, coverType){
    return this.http.put(`http://localhost:8080/api/dto/cover_type/${name}`, coverType)
  }

  deleteCoverType(name){
    return this.http.delete(`http://localhost:8080/api/dto/cover_type/${name}`);
  }
}

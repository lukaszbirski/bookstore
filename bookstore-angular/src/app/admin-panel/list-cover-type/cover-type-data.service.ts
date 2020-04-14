import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { CoverType } from './list-cover-type.component';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class CoverTypeDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllCoverTypes(): Observable<CoverType[]> {
    return this.http.get<CoverType[]>(`http://localhost:8080/api/dto/cover_type`);
  }

  retrieveCoverType(name): Observable<CoverType> {
    return this.http.get<CoverType>(`http://localhost:8080/api/dto/cover_type/${name}`);
  }

  createCoverType(name, coverType): Observable<any>  {
    return this.http.post(`http://localhost:8080/api/dto/cover_type/${name}`, coverType).pipe(
      catchError(this.handleError)
    );
  }

  updateCoverType(name, coverType): Observable<any> {
    return this.http.put(`http://localhost:8080/api/dto/cover_type/${name}`, coverType).pipe(
      catchError(this.handleError)
    );
  }

  deleteCoverType(name): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/dto/cover_type/${name}`);
  }

  handleError(error: HttpErrorResponse) {
    return throwError(error.error.name);
  }
}

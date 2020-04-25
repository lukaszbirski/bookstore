import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(
    private http: HttpClient
  ) { }

  uploadFile(formData): Observable<any> {
    return this.http.post(`http://localhost:8080/api/v1/files`, formData);
  }

  deleteFile(fileName): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/v1/files/delete/${fileName}`);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_FILE} from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(
    private http: HttpClient
  ) { }

  uploadFile(formData): Observable<any> {
    return this.http.post(`${API_FILE}`, formData);
  }

  deleteFile(fileName): Observable<any> {
    return this.http.delete(`${API_FILE}/delete/${fileName}`);
  }
}

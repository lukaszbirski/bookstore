import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Comment } from '../../admin-panel/list-comments/list-comments.component';
import {API_URL} from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class CommentDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllComments() {
    return this.http.get<Comment[]>(`${API_URL}/comments`);
  }

  retrieveCommentForBook(bookTitle) {
    return this.http.get<Comment[]>(`${API_URL}/comments/${bookTitle}`);
  }

  retrieveComment(bookTitle, author) {
    return this.http.get<Comment>(`${API_URL}/comments/${author}&${bookTitle}`);
  }

  createComment(bookTitle, author, comment) {
    return this.http.post(`${API_URL}/comments/${author}&${bookTitle}`, comment);
  }

  updateComment(bookTitle, author, comment) {
    return this.http.put(`${API_URL}/comments/${author}&${bookTitle}`, comment);
  }

  deleteComment(bookTitle, author) {
    return this.http.delete(`${API_URL}/comments/${author}&${bookTitle}`);
  }
}

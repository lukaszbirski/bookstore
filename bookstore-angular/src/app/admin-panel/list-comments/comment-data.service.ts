import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Comment } from './list-comments.component';

@Injectable({
  providedIn: 'root'
})
export class CommentDataService {

  constructor(
    private http: HttpClient
  ) { }

  retrieveAllComments(){
    return this.http.get<Comment[]>(`http://localhost:8080/api/dto/comments`)
  }

  retrieveCommentForBook(bookTitle){
    return this.http.get<Comment[]>(`http://localhost:8080/api/dto/comments/${bookTitle}`)
  }

  retrieveComment(bookTitle, author){
    return this.http.get<Comment>(`http://localhost:8080/api/dto/comments/${author}&${bookTitle}`)
  }

  createComment(bookTitle, author, comment){
    return this.http.post(`http://localhost:8080/api/dto/comments/${author}&${bookTitle}`, comment)
  }

  updateComment(bookTitle, author, comment){
    return this.http.put(`http://localhost:8080/api/dto/comments/${author}&${bookTitle}`, comment)
  }

  deleteComment(bookTitle, author){
    return this.http.delete(`http://localhost:8080/api/dto/comments/${author}&${bookTitle}`)
  }
}

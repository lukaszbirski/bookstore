import { Component, OnInit } from '@angular/core';
import {CommentDataService} from '../../services/http/comment-data.service';

export class Comment {
  constructor(
    public author: string,
    public bookTitle: string,
    public date: string,
    public description: string,
    public rating: number,
  ) {}
}

@Component({
  selector: 'app-list-comments',
  templateUrl: './list-comments.component.html',
  styleUrls: ['./list-comments.component.css']
})
export class ListCommentsComponent implements OnInit {

  public comments: Comment[];
  public deleteError: string;

  constructor(
    private commentsService: CommentDataService
  ) { }

  ngOnInit(): void {
    this.refreshComments();
  }

  refreshComments() {
      this.commentsService.retrieveAllComments().subscribe(
        response => {
          console.log(response);
          this.comments = response;
        }
      );
  }

  deleteComment(bookTitle, author) {
    console.log(`delete ${bookTitle}, ${author}`);
    this.commentsService.deleteComment(bookTitle, author).subscribe(
      response => {
        this.refreshComments();
      }, error => {
        this.deleteError = error.error.text;
        this.refreshComments();
      }
    );
  }

}

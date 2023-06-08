import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptorService implements  HttpInterceptor {

    constructor(private messageService: MessageService,private router :Router) { }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(request).pipe(
        retry(1),
        catchError((err) => {
          let message = ""
          if (err.status === 401) {
            //refresh token or navigate to login
            message = "Token has expired or you should be logged in"
            localStorage.removeItem("token")
            this.router.navigate["/login"]
          }
          else if (err.status === 404) {
            message = "404"
          }
          else if (err.status === 400) {
            //some message
            message = "400"
          }
          else {
            //global message for error
            message = "Unexpected error"
          }
          this.messageService.add({severity: 'error', summary: 'error '+err.status, detail: message, life: 3000});
          return throwError(err)
        })
      )
    }

  }

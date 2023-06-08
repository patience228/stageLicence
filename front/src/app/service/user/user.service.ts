import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/model/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {


    constructor(private http: HttpClient) { }

    addUser(user:User){
      return this.http.post<any>(environment.user_url+"/save",user)
      .toPromise();
  }

  updateUser(user:User){
      return this.http.post<any>(environment.user_url+"/update",user)
      .toPromise();
  }

  deleteUser(user:User){
      return this.http.delete<any>(environment.user_url+"/"+user.id)
      .toPromise();
  }

  getUsers() {
      return this.http.get<any>(environment.user_url)
      .toPromise();
  }

  getUserById(user:User) {
      return this.http.get<any>(environment.user_url+"/find/"+user.id)
      .toPromise();
  }

  getUsersCount() {
      return this.http.get<any>(environment.user_url+"/count")
      .toPromise();
  }

}

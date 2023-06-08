import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

    constructor(private http: HttpClient) { }

  login(data: any): Promise<any> {
    return this.http.post(environment.root_url+"gevisco/api/security/auth/login?username="+data.username+"&password="+data.password,"").toPromise();
 }
     forget_password(data: any): Promise<any> {
         throw new Error("Method not implemented.");
     }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Scolarite } from 'src/app/model/scolarite';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ScolariteserviceService {


    constructor(private http: HttpClient) { }

    addScolarite(scolarite:Scolarite){
      return this.http.post<any>(environment.scolarite_url+"/save",scolarite)
      .toPromise();
  }

  updateScolarite(scolarite:Scolarite){
      return this.http.post<any>(environment.scolarite_url+"/update",scolarite)
      .toPromise();
  }

  deleteScolarite(scolarite:Scolarite){
      return this.http.delete<any>(environment.scolarite_url+"/"+scolarite.id)
      .toPromise();
  }

  getScolarites() {
      return this.http.get<any>(environment.scolarite_url)
      .toPromise();
  }

  getScolariteById(scolarite:Scolarite) {
      return this.http.get<any>(environment.scolarite_url+"/find/"+scolarite.id)
      .toPromise();
  }

  getScolaritesCount() {
      return this.http.get<any>(environment.scolarite_url+"/count")
      .toPromise();
  }

}

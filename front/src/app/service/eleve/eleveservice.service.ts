import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Eleve } from 'src/app/model/eleve';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EleveserviceService {

  constructor(private http: HttpClient) { }
  addEleve(eleve:Eleve){
    return this.http.post<any>(environment.eleve_url+"/save",eleve)
    .toPromise();
}

updateEleve(eleve:Eleve){
    return this.http.post<any>(environment.eleve_url+"/update",eleve)
    .toPromise();
}

deleteEleve(eleve:Eleve){
    return this.http.delete<any>(environment.eleve_url+"/"+eleve.id)
    .toPromise();
}

getEleves() {
    return this.http.get<any>(environment.eleve_url)
    .toPromise();
}

getEleveById(eleve:Eleve) {
    return this.http.get<any>(environment.eleve_url+"/find/"+eleve.id)
    .toPromise();
}

getEleve(id:string) {
    return this.http.get<any>(environment.eleve_url+"/find/"+id)
    .toPromise();
}

getElevesCount() {
    return this.http.get<any>(environment.eleve_url+"/count")
    .toPromise();
}



getElevesInscrire() {
    return this.http.get<any>(environment.eleve_url+"/eleve")
    .toPromise();
}



}

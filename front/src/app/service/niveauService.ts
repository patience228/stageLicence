import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Niveau } from '../model/niveau';

@Injectable()
export class NiveauService {

    constructor(private http: HttpClient) { }

    addNiveau(niveau:Niveau){
        return this.http.post<any>(environment.niveau_url+"/save",niveau)
        .toPromise();
    }

    updateNiveau(niveau:Niveau){
        return this.http.post<any>(environment.niveau_url+"/update",niveau)
        .toPromise();
    }

deleteNiveau(niveau:Niveau){
        return this.http.delete<any>(environment.niveau_url+"/"+niveau.id)
        .toPromise();
    }

    getNiveaux() {
        return this.http.get<any>(environment.niveau_url)
        .toPromise();
    }

    getNiveauById(niveau:Niveau) {
        return this.http.get<any>(environment.niveau_url+"/find/"+niveau.id)
        .toPromise();
    }

    getNiveauxCount() {
        return this.http.get<any>(environment.niveau_url+"/count")
        .toPromise();

    }


}

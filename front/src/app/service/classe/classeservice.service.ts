import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Classe } from 'src/app/model/classe';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClasseserviceService {

  constructor(private http: HttpClient) { }
  addClasse(classe:Classe){
    return this.http.post<any>(environment.classe_url+"/save",classe)
    .toPromise();

}

updateClasse(classe:Classe){
    return this.http.post<any>(environment.classe_url+"/update",classe)
    .toPromise();

}

deleteClasse(classe:Classe){
    return this.http.delete<any>(environment.classe_url+"/"+classe.id)
    .toPromise();
}

getClasses() {
    return this.http.get<any>(environment.classe_url)
    .toPromise();
}

getClasseById(classe:Classe) {
    return this.http.get<any>(environment.classe_url+"/find/"+classe.id)
    .toPromise();
}

getClassesCount() {
    return this.http.get<any>(environment.classe_url+"/count")
    .toPromise();
}


}

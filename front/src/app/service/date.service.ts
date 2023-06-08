import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  constructor() { }

  /***
   *@description: Si la fonction retourne un nombre positif alors la date en premier paramètre est la plus supérieur
   *Sinon elle est la plus inférieur
   *si le résultat est null alors elles sont égales
   *@param data1 : Date
   *@param data2 : Date
   *@returns : number
   */
  conpareTwoDates(date1:Date,date2:Date){
      return date2.getTime()-date1.getTime()
  }
}

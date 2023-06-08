import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root'
})
export class MytranslateService {
    constructor(private translate: TranslateService,)
    {
      let lang=localStorage.getItem('lang')? localStorage.getItem('lang'):'fr';
      localStorage.setItem('lang',lang)
      translate.setDefaultLang(localStorage.getItem('lang'));
    }
  
    instant(word)
    {
      return this.translate.instant(word)
    }
}

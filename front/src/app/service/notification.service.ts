import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

    constructor(private service: MessageService) { }

   
  /**
   *
   * @param severity
   * -warn, error, success, info
   * @param title
   * @param message
   * @param time
   */
    showPrimeNgToast(severity,title?,message?,time?) {
      this.service.add({ severity: severity, summary: title, detail: message,life:time});
  }
  
}

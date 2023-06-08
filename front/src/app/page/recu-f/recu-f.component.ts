import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Preinscription } from 'src/app/model/preinscription';
import { PreinscriptionserviceService } from 'src/app/service/preinscription/preinscriptionservice.service';

@Component({
  selector: 'app-recu-f',
  templateUrl: './recu-f.component.html',
  styleUrls: ['./recu-f.component.scss']
})
export class RecuFComponent implements OnInit {

    id: string;
    preinscription: Preinscription ;
    a:string;
    

  constructor(private preinscriptionService: PreinscriptionserviceService,private route: ActivatedRoute,
    private router: Router) { }


 ngOnInit(): void {
   // this.inscription = new Inscription();
    this.a=this.createId();

   this.id = this.route.snapshot.params['id'];

    this.preinscriptionService.getPrenscription(this.id)
      .then(data => {
        console.log(data)
        this.preinscription = data;
      }, error => console.log(error));
  }

  print() {
    window.print();
}

list(){
    this.router.navigate(['page/preinscription']);
}

createId(): string {
    let id = 'EPL_';
    const chars='0123456789';
    for (let i = 0; i < 8; i++) {
        id += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return id;
}

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Inscription } from 'src/app/model/inscription';
import { Scolarite } from 'src/app/model/scolarite';
import { InscriptionserviceService } from 'src/app/service/inscription/inscriptionservice.service';
import { ScolariteserviceService } from 'src/app/service/scolarite/scolariteservice.service';

@Component({
  selector: 'app-precu',
  templateUrl: './precu.component.html',
  styleUrls: ['./precu.component.scss']
})
export class PrecuComponent implements OnInit {
   id: string;
    inscription: Inscription ;
    a:string;
    scolarites:Scolarite;

  constructor(private inscriptionService: InscriptionserviceService,private route: ActivatedRoute,
    private router: Router, private scolariteService: ScolariteserviceService) { }

 ngOnInit(): void {
   // this.inscription = new Inscription();
    this.a=this.createId();
    this.scolariteService.getScolarites().then(data=>{this.scolarites=data;})

   this.id = this.route.snapshot.params['id'];

    this.inscriptionService.getInscription(this.id)
      .then(data => {
        console.log(data)
        this.inscription = data;
      }, error => console.log(error));
  }

  print() {
    window.print();
}

list(){
    this.router.navigate(['page/inscription']);
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

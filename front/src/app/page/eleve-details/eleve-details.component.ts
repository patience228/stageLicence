import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Affectation } from 'src/app/model/affectation';
import { Eleve } from 'src/app/model/eleve';
import { Inscription } from 'src/app/model/inscription';
import { Scolarite } from 'src/app/model/scolarite';
import { AffectationService } from 'src/app/service/affectation/affectation.service';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { InscriptionserviceService } from 'src/app/service/inscription/inscriptionservice.service';
import { ScolariteserviceService } from 'src/app/service/scolarite/scolariteservice.service';

@Component({
  selector: 'app-eleve-details',
  templateUrl: './eleve-details.component.html',
  styleUrls: ['./eleve-details.component.scss']
})
export class EleveDetailsComponent implements OnInit {


  id: string ;
  eleve: Eleve ;
  inscriptions: Inscription[] ;
  affectations: Affectation[] ;
  scolarites: Scolarite[] ;

  constructor(private route: ActivatedRoute,private router: Router,
    private eleveService: EleveserviceService,private inscriptionService:InscriptionserviceService,
    private affectationService : AffectationService, private scolariteService: ScolariteserviceService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.inscriptionService.getInscriptions().then(data=>{this.inscriptions=data;console.log(data);})

    this.affectationService.getAffectations().then(data=>{this.affectations=data;console.log(data);})

    this.scolariteService.getScolarites().then(data=>{this.scolarites=data;console.log(data);})

    this.eleveService.getEleve(this.id)
    .then(data => {
        console.log(data)
        this.eleve = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['page/eleve']);
}

}

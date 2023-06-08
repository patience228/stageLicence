import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Eleve } from 'src/app/model/eleve';
import { Inscription } from 'src/app/model/inscription';
import { Preinscription } from 'src/app/model/preinscription';
import { Scolarite } from 'src/app/model/scolarite';
import { AnneeService } from 'src/app/service/anneeservice';
import { Base64Service } from 'src/app/service/conversion/base64.service';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { InscriptionserviceService } from 'src/app/service/inscription/inscriptionservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import { NiveauService } from 'src/app/service/niveauService';
import { NotificationService } from 'src/app/service/notification.service';
import { PreinscriptionserviceService } from 'src/app/service/preinscription/preinscriptionservice.service';
import { ScolariteserviceService } from 'src/app/service/scolarite/scolariteservice.service';

@Component({
  selector: 'app-preinscription-accept',
  templateUrl: './preinscription-accept.component.html',
  styleUrls: ['./preinscription-accept.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PreinscriptionAcceptComponent implements OnInit {

    preinscriptionDialog: boolean;as

    preinscription: Preinscription;

    inscription: Inscription;

    scolarites: Scolarite[];
    scolarite: Scolarite;
    scolar:any;

    disable: boolean;

    eleve: Eleve;

    preinscriptionsAcept: Preinscription[];

    niveauDatabase: any[];

    niveaux: SelectItem[]=[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];

    types: SelectItem[]=[];

    submitted: boolean;

    cols: any[];
    a:any;

    file:File;

    constructor(private preinscriptionService: PreinscriptionserviceService,private messageService: MessageService,
        private confirmationService: ConfirmationService, private niveauService: NiveauService, private router:Router,
        private anneeService: AnneeService, private inscriptionService: InscriptionserviceService,
         private scolariteService: ScolariteserviceService, private eleveService: EleveserviceService,
         private translate:MytranslateService,private notificationService: NotificationService, private fileService:Base64Service) { }

        async ngOnInit(): Promise<void> {

            this.niveauDatabase=  await this.niveauService.getNiveaux();
            this.anneeDatabase=  await this.anneeService.getAnnees();
            this.scolarites=  await this.scolariteService.getScolarites();

        this.preinscriptionsAcept=[];
        this.preinscriptionService.getPreinscriptionsbyEtat(2).then(data =>{ this.preinscriptionsAcept = data;console.log(data)});




        this.types = [
            {label: 'Togolaise', value: 'Togolaise'},
            {label: 'Senégalaise', value: 'Senégalaise'},
            {label: 'Malienne', value: 'Malienne'},
            {label: 'Ivoirienne', value: 'Ivoirienne'},
            {label: 'Ghanéenne', value: 'Ghanéenne'},
            {label: 'Béninoise', value: 'Béninoise'},
            {label: 'Nigérienne', value: 'Nigérienne'},
            {label: 'Camerounaise', value: 'Camerounaise'},
            {label: 'Gabonaise', value: 'Gabonaise'},
            {label: 'Nigériane', value: 'Nigériane'}

        ];




        this.niveauDatabase.forEach(data=>{
            this.niveaux.push({label:data.libelle , value: data})
          })

          this.anneeDatabase.forEach(data=>{
              this.annees.push({label:data.libelle , value: data})
            })


            this.cols = [
                {field: 'id', header: 'Identifiant'},
                {field: 'matricule', header: 'Matricule'},


                {field: 'montant', header: 'Montant'},
                {field: 'derniereClasse', header: 'Dernière Classe '},
                {field: 'dateInscription', header: 'Date Inscription'},
                {field: 'eleve', header: 'Elève'},
                {field: 'annee', header: 'Année'},
                {field: 'niveau', header: 'Niveau'},

            ];

            this.types = [
                {label: 'Togolaise', value: 'Togolaise'},
                {label: 'Senégalaise', value: 'Senégalaise'},
                {label: 'Malienne', value: 'Malienne'},
                {label: 'Ivoirienne', value: 'Ivoirienne'},
                {label: 'Ghanéenne', value: 'Ghanéenne'},
                {label: 'Béninoise', value: 'Béninoise'},
                {label: 'Nigérienne', value: 'Nigérienne'},
                {label: 'Camerounaise', value: 'Camerounaise'},
                {label: 'Gabonaise', value: 'Gabonaise'},
                {label: 'Nigériane', value: 'Nigériane'}

            ];


            this.a= await this.inscriptionService.getInscriptionsCount()



      }

       matricule(): string{
          let m ="EPL/";


            let b=this.a+1;console.log(b)

        m+=b;console.log(m)
        return m;
      }



      editPreinscription(preinscription: Preinscription) {
        this.preinscription = {...preinscription};

        this.inscription = {};
        this.inscription.dateInscription=new Date();

        this.eleve = {...preinscription.eleve};
        this.eleve.dateNaissance=new Date(preinscription.eleve.dateNaissance)
        this.preinscriptionDialog = true;
        this.disable=true;

    }

    hideDialog() {
        this.preinscriptionDialog = false;
        this.submitted = false;
    }


    verification(){
        if(
          this.inscription.montantVerse == null
         // this.inscription.dateInscription == null ||


          //this.eleve.matricule.length== 0


        )
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }







     async saveInscription() {
        this.submitted = true;

       // this.inscription.dateInscription=null;
       (this.inscription.dateInscription as any)=this.inscription.dateInscription?.getTime();
       (this.eleve.dateNaissance as any)=this.eleve.dateNaissance ?.getTime();

        if (this.eleve.nom.trim()) {

            if (this.preinscription.id) {

              this.inscription.eleve = this.preinscription.eleve
              this.inscription.annee = this.preinscription.annee
              this.inscription.niveau = this.preinscription.niveau
              this.inscription.derniereClasse= this.preinscription.classeAnterieure;

             // this.inscription.resteApayer= this.scolar-this.inscription.montantVerse;


                this.eleve.matricule = this.matricule();
                this.eleve.active = true;
                console.log(this.eleve.matricule);

                   this.eleveService.updateEleve(this.eleve);

                this.preinscription.etat=3;
                this.preinscriptionService.updatePreinscription(this.preinscription);


                this.eleve.active = true;
                console.log(this.eleve.active);

                   this.eleveService.updateEleve(this.eleve);

              //  this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Inscription effectuée avec succès', life: 3000});

            }

            if( this.inscriptionService.addInscription(this.inscription)){

            this.preinscriptionService.getPreinscriptionsbyEtat(2).then(data=>{
                this.notificationService.showPrimeNgToast("success","",this.translate.instant("Inscription effectuée avec succès"))
                setTimeout(() => {
                this.router.navigate(["/page/inscription"])
                }, 100);

            });
            }






        }
    }

    selectFile(event){
    console.log(event.target.files[0]);
    this.file=event.target.files[0]

    this.fileService.convertFile(this.file).subscribe(base64=>{
        console.log(base64)

        this.eleve.image="data:"+(this.file as File).type+";base64,"+base64;
    });
  }


}

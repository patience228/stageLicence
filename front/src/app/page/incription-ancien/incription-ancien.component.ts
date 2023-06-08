import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Affectation } from 'src/app/model/affectation';
import { Annee } from 'src/app/model/annee';
import { Eleve } from 'src/app/model/eleve';
import { Inscription } from 'src/app/model/inscription';
import { Scolarite } from 'src/app/model/scolarite';
import { AffectationService } from 'src/app/service/affectation/affectation.service';
import { AnneeService } from 'src/app/service/anneeservice';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { DateService } from 'src/app/service/date.service';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { InscriptionserviceService } from 'src/app/service/inscription/inscriptionservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import { NiveauService } from 'src/app/service/niveauService';
import { NotificationService } from 'src/app/service/notification.service';
import { ScolariteserviceService } from 'src/app/service/scolarite/scolariteservice.service';

@Component({
  selector: 'app-incription-ancien',
  templateUrl: './incription-ancien.component.html',
  styleUrls: ['./incription-ancien.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class IncriptionAncienComponent implements OnInit {

    eleveDialog: boolean;as

    eleves: Eleve[];

    eleve: Eleve;

    inscription: Inscription;

    selectedEleves: Eleve[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    niveauDatabase: any[];

    niveaux: SelectItem[]=[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];


    classeDatabase: any[];

    classes: SelectItem[]=[];

    eleveDatabase: any[];

    elevess: SelectItem[]=[];

    scolarites: Scolarite[];
    scolarite: Scolarite;

    a:Annee;
    n:number;
    an: SelectItem[]=[];
    annee:Annee[];

    an2:Annee;
    affectation:Affectation;
    affectations:Affectation[];



    constructor(private eleveService: EleveserviceService, private messageService: MessageService,
        private confirmationService: ConfirmationService,private router : Router,
        private inscriptionService: InscriptionserviceService,private niveauService: NiveauService,
        private anneeService: AnneeService, private scolariteService: ScolariteserviceService,
        private translate:MytranslateService,private notificationService: NotificationService,
        private classeService: ClasseserviceService,private dateService:DateService,private affectationService:AffectationService) { }

        async ngOnInit(): Promise<void> {

            this.niveauDatabase=  await this.niveauService.getNiveaux();
            this.anneeDatabase=  await this.anneeService.getAnnees();
            this.classeDatabase=  await this.classeService.getClasses();
           // this.eleveDatabase=  await this.eleveService.getElevesInscrire();
            this.scolarites=  await this.scolariteService.getScolarites();

        this.eleves=[];
        this.eleveService.getElevesInscrire().then(e =>{ this.eleves = e;console.log(e)
            this.annee=[];
            this.anneeService.getAnnees().then(data =>{ this.annee = data;console.log(data)
                                                        this.n=data.length-1;console.log(this.n);
                                                        this.a=data[this.n];console.log(new Date(this.a.dateFin));
                                                        this.an.push({label:this.a.libelle , value: this.a})


                                                        });
        });

        this.anneeService.getAnnees().then(data =>{ this.annee = data;console.log(data)
            let n=data.length-2;console.log(n);

            this.an2=data[n];console.log(new Date(this.an2.dateFin));
            console.log(this.an2)

        });




        this.cols = [
            {field: 'id', header: 'Identifiant'},
            {field: 'matricule', header: 'Matricule'},
            {field: 'nom', header: 'Nom'},
            {field: 'prenom', header: 'Prénoms'},
            {field: 'sexe', header: 'Sexe'},
            {field: 'adresse', header: 'Adresse'},
            {field: 'nationalite', header: 'Nationalité'},
            {field: 'dateNaissance', header: 'Date de Naissance'},
            {field: 'lieuNaissance', header: 'Lieu de Naissance'},
            {field: 'image', header: 'Image'},
            {field: 'nomParent', header: 'Nom du Parent'},
            {field: 'prenomParent', header: 'Prénoms du Parent'},
            {field: 'adresseParent', header: 'Adresse du Parent'},
            {field: 'professionParent', header: 'Profession du Parent'},
            {field: 'telephoneParent', header: 'Téléphone du Parent'},

        ];


        this.niveauDatabase.forEach(data=>{
            this.niveaux.push({label:data.libelle , value: data})
          })

          this.anneeDatabase.forEach(data=>{
              this.annees.push({label:data.libelle , value: data})
            })

            this.classeDatabase.forEach(data=>{
                this.classes.push({label:data.libelle , value: data.libelle})
              })

              this.eleveDatabase.forEach(data=>{
                this.elevess.push({label:data.nom+' '+data.prenom , value: data})
              })



      }


      activation(){

        this.confirmationService.confirm({
            message: 'Êtes-vous sûr ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

            this.selectedEleves.forEach(s=>{
                s.active=false;
                this.eleveService.updateEleve(s)
                })
             //  this.eleves = this.eleves.filter(val => !this.selectedEleves.includes(val));
                this.selectedEleves = null;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Activation réussie', life: 3000});
            }
        });

    }




    async editEleve(eleve: Eleve) {
        this.eleve = {...eleve};
        this.affectations= await this.affectationService.getAffectationsByAnnee(this.an2,eleve);console.log(this.affectations)
        this.affectation=this.affectations[0]
        console.log(this.affectation)
        this.inscription = {};
        this.inscription.dateInscription = new Date();
        this.eleveDialog = true;

        this.disable=true;

    }



    hideDialog() {
        this.eleveDialog = false;
        this.submitted = false;
    }

    verification(){
        if(this.eleve.nom.length ==0 ||
          this.eleve.prenom == null ||
          this.eleve.dateNaissance == null ||
          this.eleve.lieuNaissance == null ||
          this.eleve.sexe == null ||
          this.eleve.nationalite == null ||
          this.eleve.adresse == null ||
          this.eleve.nomParent == null ||
          this.eleve.prenomParent == null
         )
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }


    async saveInscription() {
        this.submitted = true;

        (this.inscription.dateInscription as any)=this.inscription.dateInscription?.getTime();

        this.inscription.derniereClasse=this.affectation.classe.libelle;
        if (this.eleve.nom.trim()) {

            if (this.eleve.id) {

              this.inscription.eleve = this.eleve



            }

            if( this.inscriptionService.addInscription(this.inscription)){

                this.eleve.active=true;
                this.eleveService.updateEleve( this.eleve);

            this.eleveService.getElevesInscrire().then(data=>{
                this.notificationService.showPrimeNgToast("success","",this.translate.instant("Inscription effectuée avec succès"))
                setTimeout(() => {
                this.router.navigate(["/page/inscription"])
                }, 100);


            });
            }
        }
    }


}

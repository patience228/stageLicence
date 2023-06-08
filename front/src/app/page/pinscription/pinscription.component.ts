import { Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem, MenuItem } from 'primeng/api';
import { Affectation } from 'src/app/model/affectation';
import { Annee } from 'src/app/model/annee';
import { Eleve } from 'src/app/model/eleve';
import { Inscription } from 'src/app/model/inscription';
import { Niveau } from 'src/app/model/niveau';
import * as FileSaver from 'file-saver';
import { AffectationService } from 'src/app/service/affectation/affectation.service';
import { AnneeService } from 'src/app/service/anneeservice';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { InscriptionserviceService } from 'src/app/service/inscription/inscriptionservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import { NiveauService } from 'src/app/service/niveauService';
import { NotificationService } from 'src/app/service/notification.service';

@Component({
  selector: 'app-pinscription',
  templateUrl: './pinscription.component.html',
  styleUrls: ['./pinscription.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PinscriptionComponent implements OnInit {


    inscriptionDialog: boolean;as

    inscriptions: Inscription[];

    inscription: Inscription;

    selectedInscriptions: Inscription[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    eleve: Eleve;

    niveauDatabase: any[];

    niveaux: SelectItem[]=[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];

    classeDatabase: any[];

    classes: SelectItem[]=[];

    types: SelectItem[]=[];

    affectation:Affectation;

    a:any;

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];

    constructor(private eleveService: EleveserviceService,private inscriptionService: InscriptionserviceService,private niveauService: NiveauService,
        private anneeService: AnneeService, private messageService: MessageService,private confirmationService: ConfirmationService,
        private router: Router, private affectationService: AffectationService, private classeService:ClasseserviceService,
        private translate:MytranslateService,private notificationService: NotificationService) { }

        async ngOnInit(): Promise<void> {

            this.niveauDatabase=  await this.niveauService.getNiveaux();
            this.anneeDatabase=  await this.anneeService.getAnnees();
            this.classeDatabase=  await this.classeService.getClasses();

        this.inscriptions=[];
        this.inscriptionService.getInscriptions().then(data =>{ this.inscriptions = data;console.log(data)});

        this.cols = [


            {field: 'nom', header: 'Nom'},
            {field: 'prenom', header: 'Prénoms'},
            {field: 'sexe', header: 'Sexe'},


            {field: 'derniereClasse', header: 'Dernière Classe '},

            {field: 'annee', header: 'Année'},
            {field: 'niveau', header: 'Niveau'},

        ];

        this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

        this.items = [
          {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
              this.exportPdf();
              }
          },
          {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                  if(this.selectedInscriptions)
                      (this.dt as any).exportCSV({selectionOnly:true});
                  else
                      (this.dt as any).exportCSV();
              }
          },
          {label:  this.translate.instant('En Exel'), icon: 'pi-file-excel', command: () => {
              this.exportExcel();
              }
          }
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


        this.niveauDatabase.forEach(data=>{
            this.niveaux.push({label:data.libelle , value: data})
          })

          this.anneeDatabase.forEach(data=>{
              this.annees.push({label:data.libelle , value: data})
            })





            this.a =this.createId();

      }

      editInscription(inscription: Inscription) {
        this.inscription = {...inscription};
       // this.eleve = {...inscription.eleve};
        this.affectation = {};
        this.eleve = {...inscription.eleve};
        this.inscription.niveau=inscription.niveau;

      /*  for (let i = 0; i < this.classeDatabase.length; i++) {
            if (this.classeDatabase[i].niveau.libelle == this.inscription.niveau.libelle) {
                this.classes.push({label:this.classeDatabase[i].libelle , value: this.classeDatabase[i]})
            }
          }*/

        this.classes=[]
        this.classeDatabase.forEach(data=>{
            if(this.inscription.niveau.libelle==data.niveau.libelle){
                this.classes.push({label:data.libelle , value: data})
            }
          })

        this.inscriptionDialog = true;
        this.disable=true;
    }

    hideDialog() {
        this.inscriptionDialog = false;
        this.submitted = false;
    }

    verification(){
        if(
          this.affectation.classe == null
         // this.inscription.dateInscription == null ||


          //this.eleve.matricule.length== 0


        )
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }


    async saveAffectation() {
        this.submitted = true;


        if (this.eleve.nom.trim()) {

            if (this.inscription.id) {

              this.affectation.eleve = this.inscription.eleve
              this.affectation.annee = this.inscription.annee

              this.inscription.etat=1;
              this.inscriptionService.updateInscription(this.inscription);
            }

            if( this.affectationService.addAffectation(this.affectation)){

                this.inscriptionService.getInscriptions().then(data=>{
                    this.notificationService.showPrimeNgToast("success","",this.translate.instant("Affectation effectuée avec succès"))
                    setTimeout(() => {
                    this.router.navigate(["/page/affectation"])
                    }, 100);

                });
            }
        }
    }
/*
    affectSelectedInscriptions(){

            this.confirmationService.confirm({
                message: 'Êtes-vous sûr de ?',
                header: 'Confirmer',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {

            this.selectedInscriptions.forEach(s=>{
                let eleve=s.eleve
                let annee=s.annee

                this.affectation.eleve = eleve
                this.affectation.annee = annee

                s.etat=1;
                this.inscriptionService.updateInscription(s);

                if( this.affectationService.addAffectation(this.affectation)){

                    this.inscriptionService.getInscriptions().then(data=>{
                        this.notificationService.showPrimeNgToast("success","",this.translate.instant("Affectation effectuée avec succès"))
                        setTimeout(() => {
                        this.router.navigate(["/page/affectation"])
                        }, 100);

                });
                      }

                    })



                }
            });

    }*/


    eleveRecu(id: any){
        this.router.navigate(['recu', id]);
      }

  print() {
    window.print();
  }

  createId(): string {
    let id = 'EPL_';
    const chars='0123456789';
    for (let i = 0; i < 8; i++) {
        id += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return id;
}


exportPdf() {
    let data=[];
    if(this.selectedInscriptions){
        this.selectedInscriptions.forEach(x=>{
            data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                derniereClasse:x.derniereClasse,
            nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    else{
        this.inscriptions.forEach(x=>{
            data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                derniereClasse:x.derniereClasse,
           nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
       // data=this.preinscriptionsValid
    import("jspdf").then(jsPDF => {
        import("jspdf-autotable").then(x => {
            const doc = new jsPDF.default('p','pt');
            (doc as any).autoTable(this.exportColumns, data);
            doc.save();
        })
    })
}

exportExcel() {
    let data=[];
    if(this.selectedInscriptions){
        this.selectedInscriptions.forEach(x=>{
            data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                derniereClasse:x.derniereClasse,
           nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    else{
        this.inscriptions.forEach(x=>{
            data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                derniereClasse:x.derniereClasse,
            nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    import("xlsx").then(xlsx => {
        const worksheet = xlsx.utils.json_to_sheet(data);
        const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

        let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
        if(!name)
            name="Inscriptions_"+ new Date().getTime()
        this.saveAsExcelFile(excelBuffer, name);
    });
}

saveAsExcelFile(buffer: any, fileName: string): void {
    let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
    let EXCEL_EXTENSION = '.xlsx';
    const data: Blob = new Blob([buffer], {
        type: EXCEL_TYPE
    });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
}





}

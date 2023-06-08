import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Annee } from 'src/app/model/annee';
import { Professeur } from 'src/app/model/professeur';
import { Titulaire } from 'src/app/model/titulaire';
import { AnneeService } from 'src/app/service/anneeservice';
import * as FileSaver from 'file-saver';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import { NotificationService } from 'src/app/service/notification.service';
import { ProfesseurserviceService } from 'src/app/service/professeur/professeurservice.service';
import { TitulaireService } from 'src/app/service/titulaire/titulaire.service';

@Component({
  selector: 'app-pprofesseur',
  templateUrl: './pprofesseur.component.html',
  styleUrls: ['./pprofesseur.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PprofesseurComponent implements OnInit {
    titulaireDialog: boolean;

    professeurDialog: boolean;as

    professeurs: Professeur[];

    professeur: Professeur;

    selectedProfesseurs: Professeur[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    types: SelectItem[]=[];

    titulaire:Titulaire;

    classeDatabase: any[];

    classes: SelectItem[]=[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];

    a:Annee;
    n:number;
    an: SelectItem[]=[];
    annee:Annee[];

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];


    constructor(private professeurService: ProfesseurserviceService, private messageService: MessageService,
      private confirmationService: ConfirmationService, private titulaireService:TitulaireService,
      private translate:MytranslateService,private notificationService: NotificationService,
      private router : Router, private classeService:ClasseserviceService,private anneeService: AnneeService) { }

      async ngOnInit(): Promise<void> {

        this.classeDatabase=  await this.classeService.getClasses();
        this.anneeDatabase=  await this.anneeService.getAnnees();

        this.annee=[];
        this.anneeService.getAnnees().then(data =>{ this.annee = data;console.log(data)
                                                    this.n=data.length-1;console.log(this.n);
                                                    this.a=data[this.n];console.log(new Date(this.a.dateFin));
                                                    this.an.push({label:this.a.libelle , value: this.a})


                                                    });

      this.professeurs=[];
      this.professeurService.getProfesseurs().then(data =>{ this.professeurs = data;console.log(data)});

      this.cols = [

          {field: 'nom', header: 'Nom'},
          {field: 'prenom', header: 'Prénoms'},
          {field: 'adresse', header: 'Adresse'},
          {field: 'sexe', header: 'Sexe'},
        
          {field: 'nationalite', header: 'Nationalité'},
          {field: 'telephone', header: 'Téléphone'}

      ];

      this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

      this.items = [
        {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
            this.exportPdf();
            }
        },
        {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                if(this.selectedProfesseurs)
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


      this.classeDatabase.forEach(data=>{
        this.classes.push({label:data.libelle , value: data})
      })

      this.anneeDatabase.forEach(data=>{
          this.annees.push({label:data.libelle , value: data})
        })

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


    }

   editTitulaire(professeur:Professeur) {
    this.professeur = {...professeur};
        this.titulaire = {};
        this.submitted = false;
        this.titulaireDialog = true;
        this.disable=true;
    }

    openNew() {
      this.professeur = {};
      this.professeur.dateNaissance=new Date();
      this.submitted = false;
      this.professeurDialog = true;
      this.disable=true;
  }

  deleteSelectedProfesseurs() {
      this.confirmationService.confirm({
          message: 'Êtes-vous sûr de supprimer?',
          header: 'Confirmer',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {

      this.selectedProfesseurs.forEach(s=>{
                      this.professeurService.deleteProfesseur(s)
              })
              this.professeurs = this.professeurs.filter(val => !this.selectedProfesseurs.includes(val));
              this.selectedProfesseurs = null;
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Professeurs supprimés', life: 3000});
          }
      });
  }

  editProfesseur(professeur: Professeur) {
      this.professeur = {...professeur};
      this.professeur.dateNaissance=new Date(professeur.dateNaissance)
      this.professeurDialog = true;
      this.disable=false;
  }

  deleteProfesseur(professeur: Professeur) {
      this.confirmationService.confirm({
          message: 'Êtes-vous sûr de supprimer le professeur ' + professeur.nom + ' '+ + professeur.prenom +'?',
          header: 'Confirmer',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {
             this.professeurService.deleteProfesseur(professeur)
              this.professeurs = this.professeurs.filter(val => val.id !== professeur.id);
              this.professeur = {};
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Matière supprimée', life: 3000});
          }
      });
  }

  hideDialog() {
      this.professeurDialog = false;
      this.submitted = false;
  }

  hideDialog1() {
    this.titulaireDialog = false;
    this.submitted = false;
}

  verification(){
      if(this.professeur.nom.length==0 ||
        this.professeur.prenom.length == 0 ||
        this.professeur.adresse.length ==0 ||
        this.professeur.lieuNaissance.length ==0 ||
        this.professeur.dateNaissance == null ||
        this.professeur.nationalite.length == 0 ||
        this.professeur.telephone.length ==0 ||
        this.professeur.sexe == null)
     {
      this.disable = true;
     }
     else
      this.disable =false;
  }

   async saveProfesseur() {
      this.submitted = true;

      (this.professeur.dateNaissance as any)=this.professeur.dateNaissance?.getTime();

      if (this.professeur.nom.trim()) {
          if (this.professeur.id) {
           this.professeurService.updateProfesseur(this.professeur).then(x=>{
            this.professeurService.getProfesseurs().then(data =>{ this.professeurs = data;console.log(data)})
        })
              //this.professeurs[this.findIndexById(this.professeur.id)] = this.professeur;
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
          } else {
             this.professeurService.addProfesseur(this.professeur).then(x=>{
                this.professeurService.getProfesseurs().then(data =>{ this.professeurs = data;console.log(data)})
            })

              this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
          }

         this.professeurs= await this.professeurService.getProfesseurs();
          this.professeurs = [...this.professeurs];
          this.professeurDialog = false;
          this.professeur = {};
      }
  }

  verification1(){
    if(this.titulaire.classe ==null ||
      this.titulaire.annee == null)
   {
    this.disable = true;
   }
   else
    this.disable =false;
}

  async saveTitulaire() {
    this.submitted = true;



    if (this.professeur.nom.trim()) {


        if (this.professeur.id) {

            this.titulaire.professeur = this.professeur;


            this.professeur.etat=1;
            this.professeurService.updateProfesseur(this.professeur);
          }

        if( this.titulaireService.addTitulaire(this.titulaire)){

            this.professeurService.getProfesseurs().then(data=>{
                this.notificationService.showPrimeNgToast("success","",this.translate.instant("Enrégistrement effectué avec succès"))
                setTimeout(() => {
                this.router.navigate(["/page/titulaire"])
                }, 100);

            });
        }

    }
}


  findIndexById(id: string): number {
      let index = -1;
      for (let i = 0; i < this.professeurs.length; i++) {
          if (this.professeurs[i].id === id) {
              index = i;
              break;
          }
      }
      return index;
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
    if(this.selectedProfesseurs){
        this.selectedProfesseurs.forEach(x=>{
            data.push({adresse:x.adresse,telephone:x.telephone,nationalite:x.nationalite,
            nom:x.nom,prenom:x.prenom,sexe:x.sexe})
        })
    }
    else{
        this.professeurs.forEach(x=>{
            data.push({adresse:x.adresse,telephone:x.telephone,nationalite:x.nationalite,
                nom:x.nom,prenom:x.prenom,sexe:x.sexe})
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
    if(this.selectedProfesseurs){
        this.selectedProfesseurs.forEach(x=>{
            data.push({adresse:x.adresse,telephone:x.telephone,nationalite:x.nationalite,
                nom:x.nom,prenom:x.prenom,sexe:x.sexe})
        })
    }
    else{
        this.professeurs.forEach(x=>{
            data.push({adresse:x.adresse,telephone:x.telephone,nationalite:x.nationalite,
                nom:x.nom,prenom:x.prenom,sexe:x.sexe})
        })
    }
    import("xlsx").then(xlsx => {
        const worksheet = xlsx.utils.json_to_sheet(data);
        const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

        let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
        if(!name)
            name="Professeurs_"+ new Date().getTime()
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

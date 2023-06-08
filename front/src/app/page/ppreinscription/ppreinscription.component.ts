import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import * as FileSaver from 'file-saver';
import { CountryISO, PhoneNumberFormat, SearchCountryField } from 'ngx-intl-tel-input';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Observable } from 'rxjs';
import { Annee } from 'src/app/model/annee';
import { Eleve } from 'src/app/model/eleve';
import { Niveau } from 'src/app/model/niveau';
import { Preinscription } from 'src/app/model/preinscription';
import { AnneeService } from 'src/app/service/anneeservice';
import { Base64Service } from 'src/app/service/conversion/base64.service';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import { NiveauService } from 'src/app/service/niveauService';
import { PreinscriptionserviceService } from 'src/app/service/preinscription/preinscriptionservice.service';

@Component({
  selector: 'app-ppreinscription',
  templateUrl: './ppreinscription.component.html',
  styleUrls: ['./ppreinscription.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PpreinscriptionComponent implements OnInit {


    preinscriptionDialog: boolean;as
    imageDialog: boolean;

    preinscriptions: Preinscription[];

    preinscription: Preinscription;

    selectedPreinscriptions: Preinscription[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    eleve: Eleve;

    niveauDatabase: any[];

    niveaux: SelectItem[]=[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];
    an: SelectItem[]=[];

    types: SelectItem[]=[];

    argent:string;

    n:number;
    a:Annee;

    status: string;
    statuse: string;
    statut: string;
    niveau: Niveau;
    file:File;
    file1:File;
    file2:File;

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];

    separateDialCode = false;
	SearchCountryField = SearchCountryField;
	CountryISO = CountryISO;
  PhoneNumberFormat = PhoneNumberFormat;
	preferredCountries: CountryISO[] = [CountryISO.Togo, CountryISO.Ghana];

    constructor(private eleveService: EleveserviceService,private preinscriptionService: PreinscriptionserviceService,private niveauService: NiveauService,
        private router: Router, private anneeService: AnneeService, private messageService: MessageService,private confirmationService: ConfirmationService,
        private fileService:Base64Service,private translate:MytranslateService) { }

        async ngOnInit(): Promise<void> {

            this.niveauDatabase=  await this.niveauService.getNiveaux();
          //  this.anneeDatabase=  await this.anneeService.getAnnees();

            this.anneeService.getAnnees().then(data =>{ this.anneeDatabase = data;console.log(data)
                this.n=data.length-1;console.log(this.n);
                this.a=data[this.n];console.log(new Date(this.a.dateFin));
                this.an.push({label:this.a.libelle , value: this.a})


                });



        this.preinscriptions=[];
        this.preinscriptionService.getPreinscriptionsbyEtat(1).then(data =>{ this.preinscriptions = data;console.log(data); });



        this.cols = [

            {field: 'nom', header: 'Nom'},
            {field: 'prenom', header: 'Prénoms'},
            {field: 'sexe', header: 'Sexe'},



            {field: 'moyenne', header: 'Moyenne'},
            {field: 'ecoleProvenance', header: 'Ecole de Provenance'},
            {field: 'classeAnterieure', header: 'Classe Antérieure'},
           

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
                  if(this.selectedPreinscriptions)
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

        this.argent="Frais de dossier : 3 500 FCFA";


        this.niveauDatabase.forEach(data=>{
            this.niveaux.push({label:data.libelle , value: data})
          })

          this.anneeDatabase.forEach(data=>{
              this.annees.push({label:data.libelle , value: data})
            })




                this.status="non qualifié";
                this.statuse="négociable";
                this.statut="qualifié";







      }


    onNiveauSelected(selectedNiveauId:any):void{
        this.preinscriptionService.getPreinscriptionsForSelectedNiveaubyParameter(selectedNiveauId)
        .subscribe(
            data=>{
                this.preinscriptions=data;
            }
        )
    }







      openNew() {
        this.preinscription = {};
        this.eleve = {};
        this.eleve.dateNaissance=new Date();
        this.preinscription.datePreinscription=new Date();
        this.submitted = false;
        this.preinscriptionDialog = true;
        this.disable=true;
    }

    deleteSelectedPreinscriptions() {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

        this.selectedPreinscriptions.forEach(s=>{
            let eleve=s.eleve
             this.preinscriptionService.deletePreinscription(s)
             this.eleveService.deleteEleve(eleve)
                })
                this.preinscriptions = this.preinscriptions.filter(val => !this.selectedPreinscriptions.includes(val));
                this.selectedPreinscriptions= null;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Préinscriptions supprimées', life: 3000});
            }
        });
    }

    editPreinscription(preinscription: Preinscription) {
        this.preinscription = {...preinscription};

        this.eleve = {...preinscription.eleve};
        this.eleve.dateNaissance=new Date(preinscription.eleve.dateNaissance);
        this.preinscriptionDialog = true;
        this.disable=false;
    }



    validatePreinscription(preinscription: Preinscription){

        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de valider cette préinscription ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

                this.preinscriptionService.getPreinscriptionById(preinscription)
                preinscription.etat=2;

                this.preinscriptionService.updatePreinscription(preinscription);

                this.preinscriptions = this.preinscriptions.filter(val => val.id !== preinscription.id);
                this.preinscription = {};
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Validation effectuée avec succès', life: 3000});
            }
        });

    }

    validateSelectedPreinscriptions() {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de valider ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

            this.selectedPreinscriptions.forEach(s=>{

            this.preinscriptionService.getPreinscriptionById(s)
            s.etat=2;

             this.preinscriptionService.updatePreinscription(s)
            })
                this.preinscriptions = this.preinscriptions.filter(val => !this.selectedPreinscriptions.includes(val));
                this.selectedPreinscriptions= null;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Validations effectuées avec succès', life: 3000});
            }
        });
    }

    deletePreinscription(preinscription: Preinscription) {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                let eleve=preinscription.eleve
               this.preinscriptionService.deletePreinscription(preinscription)
               this.eleveService.deleteEleve(eleve)
                this.preinscriptions = this.preinscriptions.filter(val => val.id !== preinscription.id);
                this.preinscription = {};
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Préinscription supprimée', life: 3000});
            }
        });
    }

    hideDialog() {
        this.preinscriptionDialog = false;
        this.submitted = false;
    }

    verification(){
        if(this.preinscription.classeAnterieure == null ||
          this.preinscription.ecoleProvenance == null ||
         // this.preinscription.datePreinscription == null ||
          this.preinscription.annee == null ||
          this.preinscription.niveau== null ||
          this.preinscription.moyenne == 0 ||
          this.preinscription.frais == 0 ||


          this.eleve.nom.length== 0 ||
          this.eleve.prenom.length == 0||
          this.eleve.adresse.length== 0 ||
          this.eleve.nationalite.length == 0||
          this.eleve.nomParent.length== 0 ||
          this.eleve.prenomParent.length == 0||
          this.eleve.lieuNaissance.length== 0 ||
          this.eleve.dateNaissance == null||
          this.eleve.sexe.length== 0

        )
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }







     async savePreinscription() {
        this.submitted = true;
        let file;
        (this.eleve.dateNaissance as any)=this.eleve.dateNaissance?.getTime();


      // this.preinscription.datePreinscription=new Date();
        this.preinscription.etat=1;

       // this.preinscriptionService .getBase64(file[0]) .subscribe(str => this.preinscription.bulletin1 = str);
       // this.preinscriptionService .getBase64(file[0]) .subscribe(str => this.preinscription.bulletin2 = str);
       // this.preinscriptionService .getBase64(file[0]) .subscribe(str => this.preinscription.bulletin3 = str);


        if (this.eleve.nom.trim()) {
            if (this.preinscription.id) {
                this.preinscription.eleve = await  this.eleveService.updateEleve(this.eleve);

             this.preinscriptionService.updatePreinscription(this.preinscription).then(x=>{
              this.preinscriptionService.getPreinscriptionsbyEtat(1).then(data =>{ this.preinscriptions = data;console.log(data)});
          })


                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
            } else {
               this.preinscription.eleve = await this.eleveService.addEleve(this.eleve);
               (this.preinscription.datePreinscription as any)=this.preinscription.datePreinscription ?.getTime();

               this.preinscriptionService.addPreinscription(this.preinscription).then(x=>{
                  this.preinscriptionService.getPreinscriptionsbyEtat(1).then(data =>{ this.preinscriptions = data;console.log(data)});
              })


                this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
            }

           this.preinscriptions= await this.preinscriptionService.getPreinscriptions();
            this.preinscriptions = [...this.preinscriptions];
            this.preinscriptionDialog = false;
            this.preinscription = {};
        }
    }



    Recu(id: any){
        this.router.navigate(['recuFrais', id]);
      }

  print() {
    window.print();
  }


  selectFile(event){
    console.log(event.target.files[0]);
    this.file=event.target.files[0]

    this.fileService.convertFile(this.file).subscribe(base64=>{
        console.log(base64)

        this.preinscription.bulletin1="data:"+(this.file as File).type+";base64,"+base64;
    });
  }

  selectFile1(event){
    console.log(event.target.files[0]);
    this.file1=event.target.files[0]

    this.fileService.convertFile(this.file1).subscribe(base64=>{
        console.log(base64)

        this.preinscription.bulletin2="data:"+(this.file1 as File).type+";base64,"+base64;
    });
  }

  selectFile2(event){
    console.log(event.target.files[0]);
    this.file2=event.target.files[0]

    this.fileService.convertFile(this.file2).subscribe(base64=>{
        console.log(base64)

        this.preinscription.bulletin3="data:"+(this.file2 as File).type+";base64,"+base64;
    });
  }

  handleProfilePictureInput(file) {
    this.preinscriptionService
        .getBase64(file[0])
        .subscribe(str => this.preinscription.bulletin1 = str);
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
    if(this.selectedPreinscriptions){
        this.selectedPreinscriptions.forEach(x=>{
            data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
            ecoleProvenance:x.ecoleProvenance,classeAnterieure:x.classeAnterieure,
            moyenne:x.moyenne,nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    else{
        this.preinscriptions.forEach(x=>{
            data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
            ecoleProvenance:x.ecoleProvenance,classeAnterieure:x.classeAnterieure,
            moyenne:x.moyenne,nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
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
    if(this.selectedPreinscriptions){
        this.selectedPreinscriptions.forEach(x=>{
            data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
            ecoleProvenance:x.ecoleProvenance,classeAnterieure:x.classeAnterieure,
            moyenne:x.moyenne,nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    else{
        this.preinscriptions.forEach(x=>{
            data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
            ecoleProvenance:x.ecoleProvenance,classeAnterieure:x.classeAnterieure,
            moyenne:x.moyenne,nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    import("xlsx").then(xlsx => {
        const worksheet = xlsx.utils.json_to_sheet(data);
        const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

        let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
        if(!name)
            name="Preinscriptions_"+ new Date().getTime()
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

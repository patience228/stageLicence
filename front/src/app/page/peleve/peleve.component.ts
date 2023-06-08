import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Eleve } from 'src/app/model/eleve';
import { Inscription } from 'src/app/model/inscription';
import { Scolarite } from 'src/app/model/scolarite';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { InscriptionserviceService } from 'src/app/service/inscription/inscriptionservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import * as FileSaver from 'file-saver';
import { ScolariteserviceService } from 'src/app/service/scolarite/scolariteservice.service';
import { Base64Service } from 'src/app/service/conversion/base64.service';

@Component({
  selector: 'app-peleve',
  templateUrl: './peleve.component.html',
  styleUrls: ['./peleve.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PeleveComponent implements OnInit {

    eleveDialog: boolean;as

    eleves: Eleve[];

    eleve: Eleve;

    selectedEleves: Eleve[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    types: SelectItem[]=[];
    file:File;

    inscriptionDialog:boolean;
    inscriptions:Inscription[];
    scolarites:Scolarite[];

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];




    constructor(private eleveService: EleveserviceService, private messageService: MessageService,
        private confirmationService: ConfirmationService, private router : Router,private translate:MytranslateService,
        private inscriptionService:InscriptionserviceService,private scolariteService:ScolariteserviceService, private fileService:Base64Service) { }

       ngOnInit(): void {


        this.eleves=[];
        this.eleveService.getElevesInscrire().then(data =>{ this.eleves = data;console.log(data)});


        this.cols = [

            {field: 'matricule', header: 'Matricule'},
            {field: 'nom', header: 'Nom'},
            {field: 'prenom', header: 'Prénoms'},
            {field: 'sexe', header: 'Sexe'},
            {field: 'adresse', header: 'Adresse'},
            {field: 'nationalite', header: 'Nationalité'},
            {field: 'dateNaissance', header: 'Date de Naissance'},
            {field: 'lieuNaissance', header: 'Lieu de Naissance'},
  // {field: 'image', header: 'Image'},

        ];

        this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

      this.items = [
        {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
            this.exportPdf();
            }
        },
        {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                if(this.selectedEleves)
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



      }



      eleveDetails(id: any){
        this.router.navigate(['details', id]);
      }

    editEleve(eleve: Eleve) {
        this.eleve = {...eleve};
        this.eleve.dateNaissance=new Date(eleve.dateNaissance)
        this.eleveDialog = true;
        this.disable=false;
    }

      scolarite(eleve: Eleve){
        this.eleve = {...eleve};
        console.log(this.eleve)
        this.inscriptions=[];

        this.inscriptionService.getInscriptions().then(data=>{this.inscriptions=data;console.log(data);})

        this.scolariteService.getScolarites().then(data=>{this.scolarites=data;console.log(data);})
        console.log(this.inscriptions);
          this.inscriptionDialog = true;
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

     async saveEleve() {
        this.submitted = true;

        (this.eleve.dateNaissance as any)=this.eleve.dateNaissance?.getTime();


        if (this.eleve.nom.trim()) {
            if (this.eleve.id) {
             this.eleveService.updateEleve(this.eleve).then(x=>{
              this.eleveService.getElevesInscrire().then(data =>{ this.eleves = data;console.log(data)});
          })


                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
            }

           this.eleves= await this.eleveService.getElevesInscrire();
            this.eleves = [...this.eleves];
            this.eleveDialog = false;
            this.eleve = {};
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

    exportPdf() {
        let data=[];
        if(this.selectedEleves){
            this.selectedEleves.forEach(x=>{
                data.push({matricule:x.matricule,adresse:x.adresse,lieuNaissance:x.lieuNaissance,
                nom:x.nom,prenom:x.prenom,sexe:x.sexe,nationalite:x.nationalite,dateNaissance:x.dateNaissance})
            })
        }
        else{
            this.eleves.forEach(x=>{
                data.push({matricule:x.matricule,adresse:x.adresse,lieuNaissance:x.lieuNaissance,
                    nom:x.nom,prenom:x.prenom,sexe:x.sexe,nationalite:x.nationalite,dateNaissance:x.dateNaissance})
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
        if(this.selectedEleves){
            this.selectedEleves.forEach(x=>{
                data.push({matricule:x.matricule,adresse:x.adresse,lieuNaissance:x.lieuNaissance,
                    nom:x.nom,prenom:x.prenom,sexe:x.sexe,nationalite:x.nationalite,dateNaissance:x.dateNaissance})
            })
        }
        else{
            this.eleves.forEach(x=>{
                data.push({matricule:x.matricule,adresse:x.adresse,lieuNaissance:x.lieuNaissance,
                    nom:x.nom,prenom:x.prenom,sexe:x.sexe,nationalite:x.nationalite,dateNaissance:x.dateNaissance})
            })
        }
        import("xlsx").then(xlsx => {
            const worksheet = xlsx.utils.json_to_sheet(data);
            const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
            const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

            let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
            if(!name)
                name="Elèves_"+ new Date().getTime()
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

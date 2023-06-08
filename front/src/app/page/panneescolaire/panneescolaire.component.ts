import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Annee } from 'src/app/model/annee';
import { AnneeService } from 'src/app/service/anneeservice';
import { DateService } from 'src/app/service/date.service';

@Component({
  selector: 'app-panneescolaire',
  templateUrl: './panneescolaire.component.html',
  styleUrls: ['./panneescolaire.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PanneescolaireComponent implements OnInit {

  anneeDialog: boolean;as

  annees: Annee[];

  annee: Annee;

  selectedAnnees: Annee[];

  submitted: boolean;

  disable: boolean;

  b:boolean=true;

  a:Annee;
  n:number;

  cols: any[];
  constructor(private anneeService: AnneeService, private messageService: MessageService,
    private confirmationService: ConfirmationService,private dateService:DateService) { }

  ngOnInit(): void {
      this.annees=[];
    this.anneeService.getAnnees().then(data =>{ this.annees = data;console.log(data)
                                                this.n=data.length-1;console.log(this.n);
                                                this.a=data[this.n];console.log(new Date(this.a.dateFin));

                                                if(this.dateService.conpareTwoDates(new Date(),new Date(this.a.dateFin))<=0){
                                                    this.b=false;
                                                }
                                                else{
                                                    this.b=true;
                                                }
                                                });

    this.cols = [
        {field: 'id', header: 'Identifiant'},
        {field: 'libelle', header: 'Libellé'},
        {field: 'dateDebut', header: 'Date de début'},
        {field: 'dateFin', header: 'Date de fin'}

    ];


  }

  openNew() {
    this.annee = {};
    this.submitted = false;
    this.anneeDialog = true;
    this.annee.dateDebut=new Date()
    this.annee.dateFin=new Date()

    this.disable=true;
}

deleteSelectedAnnees() {
    this.confirmationService.confirm({
        message: 'Êtes-vous sûr de supprimer?',
        header: 'Confirmer',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {

        this.selectedAnnees.forEach(s=>{
                    this.anneeService.deleteAnnee(s)
            })
           this.annees = this.annees.filter(val => !this.selectedAnnees.includes(val));
            this.selectedAnnees = null;
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Années supprimées', life: 3000});
        }
    });
}

editAnnee(annee: Annee) {
    this.annee = {...annee};
    this.anneeDialog = true;
    this.annee.dateDebut=new Date(annee.dateDebut)
    this.annee.dateFin=new Date(annee.dateFin)
    this.disable=false;
}

deleteAnnee(annee: Annee) {
    this.confirmationService.confirm({
        message: 'Êtes-vous sûr de supprimer ' + annee.libelle + '?',
        header: 'Confirmer',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
           this.anneeService.deleteAnnee(annee)
            this.annees = this.annees.filter(val => val.id !== annee.id);
            this.annee = {};
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Année supprimée', life: 3000});
        }
    });
}

hideDialog() {
    this.anneeDialog = false;
    this.submitted = false;
}

verification(){
    if(this.annee.libelle.length==0 ||
        this.annee.dateDebut==null ||
        this.annee.dateFin==null )
   {
    this.disable = true;
   }
   else
    this.disable =false;
}

 async saveAnnee() {
    this.submitted = true;

    if(this.dateService.conpareTwoDates(this.annee.dateDebut,this.annee.dateFin)<=0)
    {
        this.messageService.add({ severity: 'error', summary: ' Date invalide', detail: 'La date de début ne peut pas etre supérieure ou égale à la date de fin', life: 10000});
        return;
    }

    (this.annee.dateDebut as any)=this.annee.dateDebut?.getTime();
    (this.annee.dateFin as any)=this.annee.dateFin?.getTime();
    console.log(this.annee)

    if (this.annee.libelle.trim()) {
        if (this.annee.id) {
            this.anneeService.updateAnnee(this.annee).then(x=>{
                this.anneeService.getAnnees().then(data =>{ this.annees = data;console.log(data)})
            })
           // this.annees[this.findIndexById(this.annee.id)] = this.annee;
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
        } else {

           this.anneeService.addAnnee(this.annee).then(x=>{
            this.anneeService.getAnnees().then(data =>{ this.annees = data;console.log(data)})
        })
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
        }
        this.annees= await this.anneeService.getAnnees();
        this.annees = [...this.annees];
        this.anneeDialog = false;
        this.annee = {};
    }
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

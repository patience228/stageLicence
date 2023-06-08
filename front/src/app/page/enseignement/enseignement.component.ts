import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Classe } from 'src/app/model/classe';
import { Enseignement } from 'src/app/model/enseignement';
import { EnseignementUpdate } from 'src/app/model/enseignementUpdate';
import { MatClasse } from 'src/app/model/matClasse';
import { Matiere } from 'src/app/model/matiere';
import { Professeur } from 'src/app/model/professeur';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { EnseignementService } from 'src/app/service/enseignement/enseignement.service';
import { MatiereserviceService } from 'src/app/service/matiere/matiereservice.service';
import { ProfesseurserviceService } from 'src/app/service/professeur/professeurservice.service';

@Component({
  selector: 'app-enseignement',
  templateUrl: './enseignement.component.html',
  styleUrls: ['./enseignement.component.scss'],
  styles: [`:host ::ng-deep .p-multiselect {
    min-width: 15rem;
}

:host ::ng-deep .multiselect-custom-virtual-scroll .p-multiselect {
    min-width: 20rem;
}

:host ::ng-deep .multiselect-custom .p-multiselect-label {
    padding-top: .25rem;
    padding-bottom: .25rem;

}

:host ::ng-deep .multiselect-custom .country-item.country-item-value {
    padding: .25rem .5rem;
    border-radius: 3px;
    display: inline-flex;
    margin-right: .5rem;
    background-color: var(--primary-color);
    color: var(--primary-color-text);
}

:host ::ng-deep .multiselect-custom .country-item.country-item-value img.flag {
    width: 17px;
}

:host ::ng-deep .multiselect-custom .country-item {
    display: flex;
    align-items: center;
}

:host ::ng-deep .multiselect-custom .country-item img.flag {
    width: 18px;
    margin-right: .5rem;
}

:host ::ng-deep .multiselect-custom .country-placeholder {
    padding: 0.25rem;
}

:host ::ng-deep .p-colorpicker {
    width: 2.5em
}
`],
  providers: [MessageService, ConfirmationService]
})
export class EnseignementComponent implements OnInit {

    enseignementDialog: boolean;as

    matiereDialog: boolean;

    matDialog: boolean;

    classeDialog: boolean;

    enseignements: Enseignement[];

    enseignement: Enseignement;

    selectedEnseignements: Enseignement[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    classeDatabase: any[];

    classes: SelectItem[]=[];
    selectedMulti: string[] = [];

    matiereDatabase: any[];

    matieres: SelectItem[]=[];
    matiere: Matiere;

    professeurDatabase: any[];

    professeurs: SelectItem[]=[];
    professeur: Professeur;

    matclasses: MatClasse[]=[];

    name = 'Dynamic Add Fields';
    name1 = 'Dynamic Add Fields';
    values = [] ;

    professeurss:Professeur[];
    matieress:Matiere[];
    classess//:Classe[];
    mat:EnseignementUpdate;
    selectedClasses=[]

    constructor(private classeService: ClasseserviceService,private matiereService: MatiereserviceService,private professeurService: ProfesseurserviceService,
        private messageService: MessageService,private enseignementService:EnseignementService,
        private confirmationService: ConfirmationService) { }

       async ngOnInit(): Promise<void> {
        this.values=[{value:""}]
        this.classeDatabase=  await this.classeService.getClasses();
        this.matiereDatabase=  await this.matiereService.getMatieres();
        this.professeurDatabase=  await this.professeurService.getProfesseurs();

        this.professeurService.getProfesseurs().then(data=>{this.professeurss=data;})

      //  this.enseignements=[];
       // this.enseignementService.getEnseignements().then(data =>{ this.enseignements = data;console.log(data)});

        this.cols = [
            {field: 'id', header: 'Identifiant'},
            {field: 'professeur', header: 'Professeur'},
            {field: 'classe', header: 'Classe'},
            {field: 'matiere', header: 'Matière'}
        ];

        this.values.forEach(data=>{
          //  this.matclasses.push({matiere: this.values.find(x=>(value.)) })
          })

        this.classeDatabase.forEach(data=>{
          this.classes.push({label:data.libelle , value: data})
        })

        this.matiereDatabase.forEach(data=>{
            this.matieres.push({label:data.libelle , value: data})
          })

          this.professeurDatabase.forEach(data=>{
            this.professeurs.push({label:data.nom +' '+data.prenom , value: data})
          })

      }


      removevalue(i){
        this.values.splice(i,1) ;

      }

      addvalue(){
        this.values.push({value:"" });

      }




      openNew() {
        this.enseignement = {};
        this.submitted = false;
        this.enseignementDialog = true;
        this.disable=true;
    }

    deleteSelectedEnseignements() {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

        this.selectedClasses.forEach(s=>{
            this.enseignementService.deleteEnseignement(this.professeur,s,this.matiere)
        })
      //          this.enseignements = this.enseignements.filter(val => !this.selectedEnseignements.includes(val));
        this.editClasse(this.matiere);
      this.selectedEnseignements= null;

                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Enseignements supprimés', life: 3000});
            }
        });
    }

    editEnseignement(enseignement: Enseignement) {
        this.enseignement = {...enseignement};
        this.enseignementDialog = true;
        this.disable=true;
    }

//fonction edit pour la matiere
    modifMat(matiere:Matiere){
        this.mat={};
        this.mat.matiereOld=matiere;
       console.log(matiere);

        this.matDialog = true;
        this.disable=true;
    }

    //la fonction upadate de matiere
    updateMat(){
        this.mat.professeur=this.professeur;
       // this.mat.matiereOld=this.matiere;

        this.enseignementService.updateMatiereEnseignement(this.mat);
        this.messageService.add({severity: 'success', summary: 'Succès', detail: ' Modification réussie', life: 3000});
        this.matDialog = false;
        this.editMatiere(this.professeur);
    }


    //liste des matieres par professeur
    editMatiere(professeur: Professeur) {
      //  this.enseignement.professeur = {...professeur};
      this.professeur=professeur
        this.matiereDialog = true;
        this.enseignementService.getMatierebyProfesseur(professeur).then(data=>{this.matieress=data;});
    }

    //liste des classes par professeur et matieres
    editClasse(matiere: Matiere) {
        this.matiere=matiere
        console.log("prof",this.professeur,"matiere",matiere)
          this.classeDialog = true;
          this.enseignementService.getClassesbyProfesseurAndMatiere(this.professeur,matiere).then(data=>{this.classess=data;console.log(data);});
      }

      //la fonction delete
    deleteEnseignement(clase: Classe) {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                console.log(clase);
               this.enseignementService.deleteEnseignement(this.professeur,clase,this.matiere)
               // this.classess = this.classess.filter(val => val.id !== clase.id);

               this.editClasse(this.matiere);
                this.messageService.add({severity: 'success', summary: 'Succès', detail: ' Suppression réussie', life: 3000});
            }
        });
    }

    hideDialog() {
        this.enseignementDialog = false;
        this.submitted = false;
    }

    hideDialog1() {
        this.matDialog = false;
        this.submitted = false;
    }

    verif(){
        if(this.mat.matiere ==null )
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }

    verification(){
        if(this.enseignement.professeur ==null )
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }

     async saveEnseignement() {
        this.submitted = true;

        this.values.forEach(data=>{
            let tb=[]
            data.value1.forEach(d=>{
                tb.push(d.value)
            })
            this.matclasses.push({matiere:data.value,classe:tb})

        })

        console.log(this.matclasses)
        if (this.enseignement.professeur) {
            if (this.enseignement.id) {
             this.enseignementService.updateEnseignement(this.enseignement).then(x=>{
              this.enseignementService.getEnseignements().then(data =>{ this.enseignements = data;console.log(data)});
          })

               // this.enseignements[this.findIndexById(this.enseignement.id)] = this.enseignement;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
            } else {

            this.matclasses.forEach(data=>{

                this.enseignement.classe=data.classe
                this.enseignement.matiere=data.matiere

                this.enseignementService.addEnseignement(this.enseignement).then(x=>{
                    this.professeurService.getProfesseurs().then(a=>{this.professeurss=a;})
                })
            })



                this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
            }
           ;

           this.enseignements= await this.enseignementService.getEnseignements();
            this.enseignements = [...this.enseignements];
            this.enseignementDialog = false;
            this.enseignement = {};
        }
    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.enseignements.length; i++) {
            if (this.enseignements[i].id === id) {
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


}

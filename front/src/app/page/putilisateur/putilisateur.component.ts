import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { User } from 'src/app/model/user';
import { Utilisateur } from 'src/app/model/utilisateur';
import { UserService } from 'src/app/service/user/user.service';
import { UtilisateurserviceService } from 'src/app/service/utilisateur/utilisateurservice.service';

@Component({
  selector: 'app-putilisateur',
  templateUrl: './putilisateur.component.html',
  styleUrls: ['./putilisateur.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PutilisateurComponent implements OnInit {


    userDialog: boolean;as

    users: User[];

    user: User;

    selectedUsers: User[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    utilisateur: Utilisateur;

    types:SelectItem[]=[];




    constructor(private utilisateurService: UtilisateurserviceService,private userService: UserService, private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

       ngOnInit(): void {


        this.users=[];
        this.userService.getUsers().then(data =>{ this.users = data;console.log(data)});

        this.cols = [
            {field: 'id', header: 'Identifiant'},
            {field: 'email', header: 'Email'},
            {field: 'username', header: 'Username'},
            {field: 'lastName', header: 'Prénoms'},
            {field: 'firstName', header: 'Nom'},
            {field: 'password', header: 'Mot de passe'},
            {field: 'confirmPassword', header: 'Confirmation'},
            {field: 'fonction', header: 'Fonction'},
            {field: 'utilisateur', header: 'Utilisateur'}
        ];

        this.types = [
            {label: 'Responsable', value: 'Responsable'},
            {label: 'Caissier', value: 'Caissier'},
            {label: 'Secrétaire', value: 'Secrétaire'},
            {label: 'Chargé de bulletin', value: 'Chargé de bulletin'}

        ];

      }





      openNew() {
        this.user = {};
        this.utilisateur = {};
        this.submitted = false;
        this.userDialog = true;
        this.disable=true;
    }

    deleteSelectedUsers() {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

        this.selectedUsers.forEach(s=>{
                        this.userService.deleteUser(s)
                })
                this.users = this.users.filter(val => !this.selectedUsers.includes(val));
                this.selectedUsers= null;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Utilisateurs supprimés', life: 3000});
            }
        });
    }

    editUser(user: User) {
        this.user = {...user};
        this.utilisateur={...user.utilisateur}
        this.userDialog = true;
        this.disable=false;
    }

    deleteUser(user: User) {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer cet utilisateur ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                let utilisateur=user.utilisateur
               this.userService.deleteUser(user)
               this.utilisateurService.deleteUtilisateur(utilisateur)
                this.users = this.users.filter(val => val.id !== user.id);
                this.user = {};
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'utilisateur supprimé', life: 3000});
            }
        });
    }

    hideDialog() {
        this.userDialog = false;
        this.submitted = false;
    }
    a(){

        if(!this.user.id && this.utilisateur.password.length == 0  && this.utilisateur.confirmPassword.length == 0 &&
            this.user.fonction.length != 0 &&
          this.utilisateur.firstName.length != 0 &&
          this.utilisateur.email.length != 0 &&
          this.utilisateur.lastName.length != 0 &&
          this.utilisateur.username.length != 0  )
            this.disable=false;



    }

    verif(){
        if(!this.user.id && !this.utilisateur.id && (this.utilisateur.password.length==null ||
            this.utilisateur.confirmPassword.length==null)
    
          
          )
       {
        this.disable = true;
       }
       else
        this.disable =false;

      /*  if(!this.user.id  )
            this.disable=false;*/

    }






    verification(){
        if(this.user.fonction == null ||
          this.utilisateur.firstName.length == 0 ||
          this.utilisateur.email.length == 0 ||
          this.utilisateur.lastName.length == 0 ||
          this.utilisateur.username.length == 0 
          
          )
       {
        this.disable = true;
       }
       else
        this.disable =false;

      /*  if(!this.user.id  )
            this.disable=false;*/

    }







     async saveUser() {
        this.submitted = true;



        if (this.utilisateur.firstName.trim()) {
            if (this.user.id) {

              this.user.utilisateur= await this.utilisateurService.updateUtilisateur(this.utilisateur)

             this.userService.updateUser(this.user).then(x=>{
              this.userService.getUsers().then(data =>{ this.users = data;console.log(data)});
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
            })


            } else {

                if(this.utilisateur.password!=this.utilisateur.confirmPassword){
                    this.messageService.add({severity: 'error', summary: 'Succès', detail: 'Le mot de passe ainsi que la confirmation doivent être les mêmes.', life: 3000});
                    return;
                }

                if(this.utilisateur.password.length<8){
                    this.messageService.add({severity: 'error', summary: 'Succès', detail: 'Le mot de passe doit contenir au minimum huit caractères.', life: 3000});
                    return;
                }

                this.user.utilisateur= await this.utilisateurService.addUtilisateur(this.utilisateur)
               this.userService.addUser(this.user).then(x=>{
                  this.userService.getUsers().then(data =>{ this.users = data;console.log(data)});
              })


                this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
            }

           this.users= await this.userService.getUsers();
            this.users = [...this.users];
            this.userDialog = false;
            this.user = {};
        }
    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.users.length; i++) {
            if (this.users[i].id === id) {
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

import {Component, OnDestroy, OnInit} from '@angular/core';
import { AppComponent } from './app.component';
import { AppMainComponent } from './app.main.component';
import { Subscription } from 'rxjs';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';
import { Annee } from './model/annee';
import { AnneeService } from './service/anneeservice';

@Component({
  selector: 'app-topbar',
  templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent implements OnDestroy,OnInit{

    subscription: Subscription;

    items: MenuItem[];

    a:string;
    n:number;
    annee:Annee[];

    photoUrl="assets/layout/images/profil.png"
    constructor(public app: AppComponent, public appMain: AppMainComponent,private router:Router,
        private anneeService:AnneeService) {}


    ngOnInit(): void{
        this.annee=[];
        this.anneeService.getAnnees().then(data =>{ this.annee = data;console.log(data)
                                                    this.n=data.length-1;console.log(this.n);
                                                    this.a=data[this.n].libelle;
                                                    
    
    
                                                    });
    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }

    deconnecter(){
        localStorage.clear();
        this.router.navigate(["/login"]);
    }

  
}

import { Niveau } from "./niveau";

export interface Classe {
    id?:string;
    libelle?:string;
    niveau?:Niveau;
    active?:boolean;
}


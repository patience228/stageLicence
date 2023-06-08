import { Annee } from "./annee";
import { Niveau } from "./niveau";

export interface Scolarite {
    id?:string;
    montantScolarite?:number;
    annee?:Annee;
    niveau?:Niveau;
    active?:boolean;
}

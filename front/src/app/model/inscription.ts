import { Annee } from "./annee";
import { Eleve } from "./eleve";
import { Niveau } from "./niveau";

export interface Inscription {
    id?:string;
    montantVerse?:number;
    resteApayer?:number;
    derniereClasse?:string;
    dateInscription?:Date;
    niveau?:Niveau;
    annee?:Annee;
    eleve?:Eleve;
    preinscription?:string;
    etat?:number;
    active?:boolean;
}


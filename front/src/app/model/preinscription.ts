import { Annee } from "./annee";
import { Eleve } from "./eleve";
import { Niveau } from "./niveau";

export interface Preinscription {
    id?:string;
    frais?:number;
    moyenne?:number;
    ecoleProvenance?:string;
    classeAnterieure?:string;
    datePreinscription?:Date;
    eleve?:Eleve;
    annee?:Annee;
    niveau?:Niveau;
    etat?:number;
    active?:boolean;
    bulletin1?:string;
    bulletin2?:string;
    bulletin3?:string;

}





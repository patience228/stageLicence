import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): boolean {
            if(!localStorage.getItem("token"))
            {
                alert("Bonjour monsieur/madame, vous devez vous connecter pour continuer")
                return false;
            }
    
        return true;
      }
  
}

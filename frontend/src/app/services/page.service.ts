import { environment } from '../../environments/environment';

import { Injectable  } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Page } from '../model/page';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'  // <--provides this service in the root ModuleInjector
  })
export class PageService  {

  public constructor(public http: HttpClient) {
  }

  public get(id: number):Observable<Page> {

    return this.http.get<Page>(this.Server + '/api/page/'+id);

  }

  public getChildren(id: number):Observable<Page[]> {

    return this.http.get<Page[]>(this.Server + '/api/children/'+id);

  }

  getPageByPath(path: string): Observable<Page[]> {
    return this.http.get<Page[]>(this.Server   + '/api/path'+path);
  }

  public get Server(): string {
    return environment.server;
  }

  
}

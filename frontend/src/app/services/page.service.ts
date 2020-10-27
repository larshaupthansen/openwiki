import { environment } from '../../environments/environment';

import { Injectable  } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Page } from '../model/page';
import { Observable } from 'rxjs';

@Injectable()

export class PageService  {

  public constructor(public http: HttpClient) {
  }

  public get(id: number):Observable<Page> {

    return this.http.get<Page>(this.Server + '/api/page/'+id);

  }

  public get Server(): string {
    return environment.server;
  }

}

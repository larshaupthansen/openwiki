import { Component, OnInit } from '@angular/core';
import { Page } from './model/page';
import { PageService } from './services/page.service';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent  implements OnInit {
  title = 'openwiki';
  path = "";
  content:SafeHtml ="Loading...";

  page: Page = null;
  public constructor(public pageService: PageService,
                    private sanitized: DomSanitizer) {
  }

  ngOnInit() { 
    this.path = window.location.pathname; 

    this.pageService.getPageByPath(this.path).toPromise().then(page => {

      this.page = page;
      this.title = page.title;
      this.content = this.sanitized.bypassSecurityTrustHtml(page.content);
      console.log(this.page);
    });
  }
}

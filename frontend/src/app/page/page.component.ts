import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { Page } from '../model/page';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.less']
})
export class PageComponent implements OnInit, OnChanges {

  @Input()
  page : Page = null;
  title ="";
  content: SafeHtml = "";
  
  constructor( private sanitized: DomSanitizer) { }

  ngOnInit(): void {

  }
  ngOnChanges(changes: SimpleChanges) {
    for (const propName in changes) {
      const chng = changes[propName];
      var newPageValue = <Page> chng.currentValue;
      if(newPageValue  != null) {
        this.content = this.sanitized.bypassSecurityTrustHtml(newPageValue.content);
        this.title = newPageValue.title;
      }
    }
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { Page } from '../model/page';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.less']
})
export class BreadcrumbComponent implements OnInit {

  @Input()
  pages : Page[] = null;

  constructor() { }

  ngOnInit(): void {
  }

}

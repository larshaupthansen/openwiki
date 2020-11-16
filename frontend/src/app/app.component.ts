import { AfterContentInit, Component, OnInit, ViewChild } from '@angular/core';
import { TreeComponent, TreeNode } from '@circlon/angular-tree-component';
import { Page } from './model/page';
import { PageService } from './services/page.service';
import {  map } from 'rxjs/operators';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent  implements OnInit {
  path = "";

  page: Page = null;
  pages: Page[] = null;

  nodes = [
  ];  

  options = {
    idField: 'id',
    displayField: "title",
    getChildren: (node: TreeNode):any => { return null;} 
  };

  @ViewChild(TreeComponent)
  private tree: TreeComponent;

  public constructor(public pageService: PageService) {
  }


  ngOnInit() { 
    this.path = window.location.pathname; 

    this.pageService.getPageByPath(this.path).toPromise().then(pages => {

    this.pages = pages;
    this.page = pages[pages.length-1];

    this.options.getChildren = (n: TreeNode) => { return this.getChildren(n); };

   });
   this.pageService.get(1).toPromise().then(p => {
      this.nodes.push(p);
      p.hasChildren = true;
      this.tree.treeModel.update();
   });
}

  getChildren(node : TreeNode) {

    console.log("getChildren()");

    //pipe(      map(p => p.forEach(p => p.hasChildren = true))).
    this.pageService.getChildren(node.id).forEach( p=> p.forEach(p => p.hasChildren=true));
  }

  onTreeActivate(event: any) {
    console.log(event.node.data.absoluteUri);
  
  }
}

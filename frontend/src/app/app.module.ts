import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { PageComponent } from './page/page.component';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';
import { TreeModule } from '@circlon/angular-tree-component';

@NgModule({
  declarations: [
    AppComponent,
    PageComponent,
    BreadcrumbComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    TreeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

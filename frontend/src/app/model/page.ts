export class Page {
    
    id: number;
    
    title: string;

    content: string;

    uri: string;
    
    parentId: number;

    parent: Page;

    hasChildren: boolean = true;
    
    public get absoluteUrl(): string {

        if(this.parent != null) {
            var parentUri= this.parent.absoluteUrl;
            
            return  parentUri + (parentUri.endsWith("/") ? "": "/") + this.uri;
        }
        return "/";
      }
    
  }
  
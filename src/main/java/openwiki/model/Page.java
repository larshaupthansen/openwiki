package openwiki.model;

public class Page {
    

    private int id;
    private int parentId;


    private String uri;
    private String title;

    private String content;
    private Page parent;

    public int getId()  { return id; }
    public void setId(int id) { this.id = id; }

    public int getParentId()  { return this.parentId; }
    public void setParentId(int parentId) { this.parentId = parentId; }

    public String getUri() { return this.uri; }
    public void setUri(String uri) { this.uri = uri; }

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public Page getParent() { return this.parent;}
    public void setParent(Page parent) { this.parent = parent; }

    public String getAbsoluteUri() {

        if(this.parent != null) {
            var parentUri= this.parent.getAbsoluteUri();
            
            return  parentUri + (parentUri.endsWith("/") ? "": "/") + this.uri;
        }
        return "/";
    }

}

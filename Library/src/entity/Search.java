package entity;

public class Search {
    //Searching specific books

    private String author; //author's name
    private String name; //book's name
    private String category; //book's category
    private int ISBN; //book's ISBN

    public Search (String author, String name, String category, int ISBN) {
        this.author = author;
        this.name  = name;
        this.category = category;
        this.ISBN = ISBN;
    }

    public static SearchBuilder builder() {
        return new SearchBuilder();
    }

    public static class SearchBuilder {

        private String author;
        private String name;
        private String category;
        private int ISBN;

        SearchBuilder(){
        }

        public SearchBuilder author(String author){
            this.author = author;
            return this;
        }

        public SearchBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SearchBuilder category(String category) {
            this.category = category;
            return this;
        }

        public SearchBuilder ISBN(int ISBN) {
            this.ISBN = ISBN;
            return this;
        }

        public Search build() {
            return new Search(author, name, category, ISBN);
        }

    }

    public String getAuthor(){
        return author;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public int getISBN(){
        return ISBN;
    }

}

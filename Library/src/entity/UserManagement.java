package entity;

public class UserManagement {

    private String id; //user's id
    private String[] read; //books that user has read
    private String[] collection; //books in user's collection;

    public UserManagement (String id, String[] read, String[] collection){
        this.id = id;
        this.read = read;
        this.collection = collection;
    }

    public static UserManagementBuilder build() {
        return new UserManagementBuilder();
    }

    public static class UserManagementBuilder {

        private String id;
        private String[] read;
        private String[] collection;

        UserManagementBuilder(){

        }

        public UserManagementBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserManagementBuilder read(String[] read){
            this.read = read;
            return this;
        }

        public UserManagementBuilder collection(String[] collection){
            this.collection = collection;
            return this;
        }
        public UserManagement build() {
            return new UserManagement(id, read, collection);
        }

    }

    public String getId() {
        return id;
    }

    public String[] getRead(){
        return read;
    }

    public String[] getCollection(){
        return collection;
    }



}

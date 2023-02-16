package task.app.task33.books;

public enum StatusBook {
    IN_STOCK("in stock"),
    ABSENT("absent");

    private String status;
    StatusBook(String status){
        this.status = status;
    }
    public String getCode(){ return status;}
}

public class Employee {
    private String fio;
    private String post;

    public Employee(){
        this.fio = null;
        this.post = null;
    }

    public Employee(String fio, String post){
        this.fio = fio;
        this.post = post;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public String getFio() {
        return fio;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fio='" + fio + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}

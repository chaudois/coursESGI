
public class Employee {

    private String name;//non accessible
    private double salary;//non accessible

    {
        System.out.println("execution obligatoire");
    }
    public static void main(String[] args){
        Employee e=new Employee();
        String s= e.toString();
    }

    public  boolean equals(Object o){//
        Employee e= (Employee) o;
        if(e.salary==this.salary && e.name.equals(this.name)){
            return true;
        }
        else{
            return false;
        }
    }
    public  Employee(String _name, int _salary) {// ne peut pas etre void, sinon il ne renvoie pas l'objet qu'il vien de creer
        name = _name;
        salary = _salary;
    }
    public  Employee() {
        name="Default";
        salary=1400;
    }
    Employee e= new Employee();
    public void print() {
        System.out.println(name +" "+ salary);
    }
    public double getSalary(){// accès en lecture à une donné privée
        return salary;
    }
    public boolean setSalary(double _salary){//accès en écriture à une donné privé
        if(_salary>0){
            salary=_salary;
            return true;
        }
        salary=1400;
        return false;
    }
    public void setName(String name){//ambiguïté entre le parametre et la variable de la classe
        this.name=name;//this.name != name. this.name est la variable de la class employee, name est le parametre envoyé à la fonction
    }
    public Employee compare(Employee e){
        if(e.salary>salary){
            return e;//renvoie le parametre
        }
        else{
            return this;//renvoie l'objet en cours
        }
    }

}

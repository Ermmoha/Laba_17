import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Создаем объект, записывающий объекты в поток
        try(ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("text.txt"));
            // Создаем объект, достающий объекты из потока
            ObjectInputStream ois = new ObjectInputStream((new FileInputStream("text.txt")))){
            // Ввод с консоли
            Scanner input = new Scanner(System.in);
            System.out.println("Введите число");
            boolean checker = true;
            Double x = input.nextDouble();
            //Пока чекер истин
            while (checker) {
                System.out.println("Введите команду (save/upload):");
                String in = input.next();
                // Создаем объект класса Solver, передавая значение введенное с консоли
                Solver solver = new Solver(x);
                //Проверяем, была ли введена команда save. (Если да, то solver записываем в поток ввода.
                if (in.equals("save")) {
                    obs.writeObject(solver);
                    System.out.println("Сохранено");
                    checker = true;
                }
                //проверяем, была ли введена upload. Если да, то проиходит чтение и изменение cheker на false.
                if (in.equals("upload")) {
                    Solver object = (Solver) ois.readObject();
                    double y = object.x;
                    double z = object.solve();
                    System.out.println(y);
                    System.out.println(z);
                    checker = false;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
class Solver implements Serializable
{
    double x;
    Solver(double x){
        this.x = x;
    }
    public double solve() {
        double y = x - Math.sin(x);
        return y;
    }

}
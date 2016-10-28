
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int quantCidades = s.nextInt();
        AG tsp = new AG(quantCidades, 50, 5);
        tsp.lerCidades();
        tsp.lerDistancias();
        tsp.printGenes();
        for (int i = 0; i < 100; i++) {
            tsp.mutaGenes();
            tsp.cruzaGenes();
        }
        tsp.printGenes();
    }

}


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AG {

    private String[] nomes;
    private int dist[][];
    private ArrayList<Gene> genes;

    public AG(int quantCidades, int quantGenes) {
        dist = new int[quantCidades][quantCidades];
        nomes = new String[quantCidades];
        genes = new ArrayList<>();
        for (int i = 0; i < quantGenes; i++) {
            genes.add(new Gene(quantCidades));
        }
    }

    public void lerCidades() {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < nomes.length; i++) {
            nomes[i] = s.next();
        }
    }

    public void lerDistancias() {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                dist[i][j] = s.nextInt();
            }
        }
    }

    private int calcFitness(Gene g) {
        int f = 0;
        for (int i = 0; i < g.cidades.length - 1; i++) {
            f += dist[g.cidades[i]][g.cidades[i + 1]];
        }
        return f;
    }

    public void printGenes() {
        for (Gene g : genes) {
            System.out.println(g.toString(nomes));
        }
    }

}

class Gene {

    public int cidades[];

    public Gene(int v[]) {
        cidades = v;
    }

    public Gene(int tam) {
        cidades = new int[tam];
        for (int i = 0; i < tam; i++) {
            cidades[i] = i;
        }
        shuffleArray(cidades);
    }

    static void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void mutacao() {
        Random rnd = ThreadLocalRandom.current();
        int quant = rnd.nextInt(cidades.length / 2);
        for (int i = 0; i < quant; i++) {
            int pos1 = rnd.nextInt(cidades.length);
            int pos2 = rnd.nextInt(cidades.length);
            int temp = cidades[pos1];
            cidades[pos1] = cidades[pos2];
            cidades[pos2] = temp;
        }
    }

    public String toString(String nomes[]) {
        String s = "" + nomes[cidades[0]];
        for (int i = 1; i < cidades.length; i++) {
            s += "->" + nomes[cidades[i]];
        }
        return s;
    }
}


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AG {

    private String[] nomes;
    private int dist[][];
    private ArrayList<Gene> genes, elite;
    private int quantElite;
    private int quantCidades, quantGenes;

    public AG(int quantCidades, int quantGenes, int quantElite) {
        this.quantElite = quantElite;
        this.quantCidades = quantCidades;
        this.quantGenes = quantGenes;
        dist = new int[quantCidades][quantCidades];
        nomes = new String[quantCidades];
        genes = new ArrayList<>();
        for (int i = 0; i < quantGenes; i++) {
            genes.add(new Gene(quantCidades, dist));
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

    public void printGenes() {
        for (Gene g : genes) {
            System.out.println(g.toString(nomes) + "\tfitness:" + g.calcFitness());
        }
        System.out.println("FIM DO PRINT");
    }

    public void mutaGenes() {
        for (Gene g : genes) {
            g.mutacao();
        }
    }

    public void cruzaGenes() {
        //pegando os elitistas
        Collections.sort(genes);
        elite = new ArrayList<>();
        for (int i = 0; i < quantElite; i++) {
            elite.add(genes.get(i));
        }
        Random rnd = ThreadLocalRandom.current();
        for (int i = 0; i < quantGenes / 2; i++) {
            Gene pai = genes.get(rnd.nextInt(quantGenes));
            Gene mae = genes.get(rnd.nextInt(quantGenes));
            int pontoCorte = rnd.nextInt(quantCidades - 3) + 2;
            int cidadesFilho1[] = new int[quantCidades];
            int cidadesFilho2[] = new int[quantCidades];
            for (int j = 0; j < pontoCorte; j++) {
                cidadesFilho1[j] = pai.cidades[j];
                cidadesFilho2[j] = mae.cidades[j];
            }
            for (int j = pontoCorte; j < quantCidades; j++) {
                cidadesFilho1[j] = mae.cidades[j];
                cidadesFilho2[j] = pai.cidades[j];
            }
            Gene filho1 = new Gene(cidadesFilho1, dist);
            Gene filho2 = new Gene(cidadesFilho2, dist);
            elite.add(filho1);
            elite.add(filho2);
        }
        for (int i = 0; i < quantElite; i++) {
            elite.remove(i + quantElite);
        }
        genes = elite;
    }

}

class Gene implements Comparable {

    public int cidades[];
    public int[][] refDist;

    public Gene(int v[], int tabela[][]) {
        refDist = tabela;
        cidades = v;
    }

    public Gene(int tam, int tabela[][]) {
        refDist = tabela;
        Random rnd = ThreadLocalRandom.current();
        cidades = new int[tam];
        for (int i = 0; i < tam; i++) {
            cidades[i] = rnd.nextInt(tam);
        }
//         shuffleArray(cidades); //usado quando cidades não se repetiam na criação
    }

    static void shuffleArray(int[] ar) {  //usado quando cidades não se repetiam na criação
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
            cidades[pos1] = rnd.nextInt(cidades.length);
        }
    }

    public int calcFitness() {
        int f = 0;
        int repetidos[] = new int[this.cidades.length];
        for (int i = 0; i < this.cidades.length; i++) {
            repetidos[i] = -1;
        }
        for (int i = 0; i < this.cidades.length - 1; i++) {
            f += refDist[this.cidades[i]][this.cidades[i + 1]];
            repetidos[this.cidades[i]]++;
            f += repetidos[this.cidades[i]] * 100; //pensalização para cada repetição de cidades

        }
        f += refDist[this.cidades[0]][this.cidades[this.cidades.length - 1]];
        repetidos[this.cidades[this.cidades.length - 1]]++;
        f += repetidos[this.cidades[this.cidades.length - 1]] * 100; //pensalização para cada repetição de cidades
        return f;
    }

    public String toString(String nomes[]) {
        String s = "" + nomes[cidades[0]];
        for (int i = 1; i < cidades.length; i++) {
            s += "->" + nomes[cidades[i]];
        }
        return s;
    }

    @Override
    public int compareTo(Object o) {
        return calcFitness() - ((Gene) o).calcFitness();
    }
}

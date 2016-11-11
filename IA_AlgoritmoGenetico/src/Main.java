
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //*
        Scanner s = new Scanner(System.in);
        System.out.println("Quantidade de cidades:");
        int quantCidades = s.nextInt();
        AG tsp = new AG(quantCidades, 50, 5);
        System.out.println("Nomes das cidades:");
        tsp.lerCidades();
        System.out.println("distancias:");
        tsp.lerDistancias();
//        tsp.printGenes();
        for (int i = 0; i < 150; i++) {
            tsp.mutaGenes();
            tsp.cruzaGenes();
        }
        tsp.printGenes();//*/
        /*
        Scanner s = new Scanner(System.in);
        System.out.println("quant cidades");
        int quantCidades = s.nextInt();
        AG tsp = new AG(quantCidades, 50, 5);
        System.out.println("cidades");
        tsp.lerCidades();
        System.out.println("pontos");
        tsp.lerPontosCartesianos();
        System.out.println("PRIMEIRA GERACAO");
        tsp.printGenes();
        System.out.println("FIM DA PRIMEIRA GERACAO");
        for (int i = 0; i < 100; i++) {
            tsp.mutaGenes();
            tsp.cruzaGenes();
        }
        tsp.printGenes(); //*/
        
        /*
        Scanner s = new Scanner(System.in);
        int quantCidades = s.nextInt();
        AG tsp = new AG(quantCidades, 50, 5);
        System.out.println("CIDADES:");
        tsp.lerCidades();
        System.out.println("PONTOS:");
        tsp.lerPontosCartesianos();
        System.out.println("DISTANCIAS");
        for(int i=0;i<quantCidades;i++){
            for(int j= 0; j<quantCidades;j++){
                System.out.print(tsp.dist[i][j]+"\t");
            }
            System.out.print("\n");
        } //*/
    }

}

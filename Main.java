import model.Graph;
import model.list.Djkstra;
import util.LoadData;

public class Main {
    public static void main(String[] args) {
        Graph graphMatriz;

        graphMatriz = LoadData.loadAdj("data/data.txt");

        System.out.println("Matriz:");
        System.out.println(graphMatriz);

        Djkstra lisGraph = LoadData.loadList("data/data.txt");

        System.out.println("Lista:");
        System.out.println(lisGraph);
    }
}
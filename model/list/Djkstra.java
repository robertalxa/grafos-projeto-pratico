package model.list;

import model.Vertex;

import java.util.HashMap;
import java.util.LinkedList;

public class Djkstra {
    private HashMap<Vertex, LinkedList<Edge>> vertices;
    private double[][] matrizCustos;
    private Vertex[][] matrizAntecessores;
    private boolean isDirected;

    public Djkstra(boolean isDirected, int qtdVertices) {
        this.vertices = new HashMap<>();
        this.matrizCustos = new double[qtdVertices][qtdVertices];
        this.matrizAntecessores = new Vertex[qtdVertices][qtdVertices];
        this.isDirected = isDirected;

        for (int i = 0; i < this.matrizCustos.length; i++) {
            for (int j = 0; j < this.matrizCustos.length; j++) {
                this.matrizCustos[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public boolean notDirected() {
        return !isDirected;
    }

    public void addVertex(Vertex v)  {
        if (vertices.get(v) != null) {
            throw new Error("Vértice " + v.getName() + " não encontrado.");
        }
        vertices.put(v, new LinkedList<>());
    }

    public boolean addEdge(Vertex u, Vertex v, double value) {
        if (vertices.get(u) == null || vertices.get(v) == null) {
            return false;
        }
        vertices.get(u).add(new Edge(v, value));
        if (notDirected()) {
            vertices.get(v).add(new Edge(u, value));
        }
        return true;
    }
}

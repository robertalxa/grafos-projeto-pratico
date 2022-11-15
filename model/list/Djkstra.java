package model.list;

import model.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Djkstra {
    private HashMap<Vertex, LinkedList<Edge>> vertices;
    private double[][] matrizCustos;
    //private Vertex[][] matrizAntecessores;
    private Vertex[] listaAntecessores;
    private boolean isDirected;

    public Djkstra(boolean isDirected, int qtdVertices) {
        this.vertices = new HashMap<>();
        this.matrizCustos = new double[qtdVertices][qtdVertices];
        //this.matrizAntecessores = new Vertex[qtdVertices][qtdVertices];
        this.listaAntecessores = new Vertex[qtdVertices];
        this.isDirected = isDirected;

        for (int i = 0; i < this.matrizCustos.length; i++) {
            for (int j = 0; j < this.matrizCustos.length; j++) {
                this.matrizCustos[i][j] = (i == j ? 0 : Integer.MAX_VALUE);
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

    public Vertex getVertexExistente(String nome) {
        List<Map.Entry<Vertex, LinkedList<Edge>>> verticesStream = vertices.entrySet().stream()
                .collect(Collectors.toList());

        for(Map.Entry<Vertex, LinkedList<Edge>> item: verticesStream){
            if(item.getKey().getName().equals(nome)) return item.getKey();
        }

        return null;
    }

    public void aplicaAlgoritmo () {
        List<Map.Entry<Vertex, LinkedList<Edge>>> listaVertices = vertices.entrySet().stream()
                .collect(Collectors.toList());

        for (Map.Entry<Vertex, LinkedList<Edge>> item: listaVertices) {
            Vertex vtPartida = item.getKey();
            LinkedList<Edge> ligacoesVtAtual = item.getValue();

            for (Edge edge: ligacoesVtAtual) {
                Vertex vtChegada = edge.getVertex();
                int localVtPartida = vtPartida.getId();
                int localVtChegada = vtChegada.getId();
                double custo = edge.getValue();

                if (custo < matrizCustos[localVtPartida][localVtChegada]) {
                    matrizCustos[localVtPartida][localVtChegada] = custo;
                    //matrizAntecessores[localVtPartida][localVtChegada] = vtChegada;
                    if (listaAntecessores[localVtChegada] == null) {
                        listaAntecessores[localVtChegada] = vtPartida;
                    } else if(matrizCustos[listaAntecessores[localVtChegada].getId()][localVtChegada] > custo){
                        listaAntecessores[localVtChegada] = vtPartida;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String conteudo = "";

        return "";
    }
}

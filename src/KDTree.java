import java.util.Comparator;
import java.util.List;

public class KDTree {

    private class Node{
        private Point p;
        private Node FilhoEsquerdo;
        private Node FilhoDireito;

        public Node(Point p){
            this.p = p;
            this.FilhoEsquerdo = null;
            this.FilhoDireito = null;
        }
    }

    private Node start;


    //Construtor
    public KDTree(List<Point> pontos){
        start = buildTree(pontos, 0);
    }

    //construção recursiva da arvore KDTree
    public Node buildTree(List<Point> pontos, int profundidade){
        if(pontos.isEmpty()){
            return null;
        }
        int axis =  profundidade % 2;
        if(axis == 0){
            pontos.sort(Comparator.comparing(p -> p.x()));
        }else{
            pontos.sort(Comparator.comparing(p -> p.y()));
        }
        int mediana = pontos.size() / 2;

        Node no = new Node(pontos.get(mediana));

        no.FilhoEsquerdo = buildTree(pontos.subList(0, mediana), profundidade + 1);
        no.FilhoDireito = buildTree(pontos.subList(mediana+1, pontos.size()), profundidade + 1);

        return no;
    }

}

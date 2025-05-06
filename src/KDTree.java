public class KDTree {

    private class Node{
        private Point p;
        private Node Pai;
        private Node FilhoEsquerdo;
        private Node FilhoDireito;

        public Node(Point p){
            this.p = p;
            this.FilhoEsquerdo = null;
            this.FilhoDireito = null;
        }
    }

    private Node start;
    private int nElementos;
    private boolean comparaX;


    //Construtor
    public KDTree(){
        start = null;
        nElementos = 0;
        comparaX = true;
    }

    public boolean estaVazia(){
        return nElementos == 0;
    }

    public int quantidadeElementos(){
        return nElementos;
    }

    public void inserePonto(Point p){
        if(nElementos == 0){
            start = new Node(p);
        }else{
            Node proximo = start;
            boolean esquerda = false;
            while(true){
                //checar a direção
                if(comparaX){
                    if(p.x() > proximo.p.x()){
                        esquerda = false;
                    }else if(p.x() < proximo.p.x()){
                        esquerda = true;
                    }
                }else{
                    if(p.y() > proximo.p.y()){
                        esquerda = false;
                    }else if(p.y() < proximo.p.y()){
                        esquerda = true;
                    }
                }

                if(esquerda){
                    if(proximo.FilhoEsquerdo == null){
                        proximo.FilhoEsquerdo = new Node(p);
                        break;
                    }else{
                        proximo = proximo.FilhoEsquerdo;
                    }
                }else{
                    if(proximo.FilhoDireito == null){
                        proximo.FilhoDireito = new Node(p);
                        break;
                    }else{
                        proximo = proximo.FilhoDireito;
                    }
                }

            }
        }
        nElementos++;
    }

}

/* *****************************************************************************
 * Grupo:
 * Alunos integrantes:
 *
 * Descrição: Esta classe define o tipo de dado Tour implementando uma
 * Lista Encadeada Circular e definindo métodos para permitir a implementação
 * de duas heurísticas para encontrar boas soluções para o TSP.
 **************************************************************************** */

import assets.*;

public class Tour {
    private class Node {
        private Point p; // valor do ponto do nó
        private Node next; // ponteiro para o próximo nó
    }

    private Node start; // primeiro nó na lista encadeada

    // cria um ciclo vazio
    public Tour() {
        start = new Node();
    }

    // cria o ciclo de 4 pontos a->b->c->d->a (para depuração)
    public Tour(Point a, Point b, Point c, Point d) {
        start = new Node();
        insertNearest(a);
        insertNearest(b);
        insertNearest(c);
        insertNearest(d);
    }

    // retorna o número de pontos neste ciclo
    public int size() {
        if (start.p == null) {
            return 0;
        } else {
            int counter = 0;
            Node current = start;
            do {
                current = current.next;
                counter += 1;
            } while (!current.equals(start));
            return counter;
        }
    }

    // retorna o comprimento deste ciclo
    public double length() {
        if (start.p == null) {
            return 0.0;
        } else {
            double distance = 0.0;
            Node current = start;
            do {
                distance += current.p.distanceTo(current.next.p);
                current = current.next;
            } while (!current.equals(start));
            return distance;
        }
    }

    // retorna uma representação em string deste ciclo
    public String toString() {
        if (start.p == null) {
            return "";
        } else {
            Node current = start;
            StringBuilder str = new StringBuilder();
            do {
                str.append(current.p.toString() + "\n");
                current = current.next;
            } while (!current.equals(start));
            return str.toString();
        }
    }

    // desenha este ciclo na tela padrão
    public void draw() {
        if (start.p != null && start.next != null) {
            Node current = start;
            do {
                current.p.drawTo(current.next.p);
                current = current.next;
            } while (!current.equals(start));
        }
    }



    // insere p usando a heurística do vizinho mais próximo
    //linear
    public void insertNearest(Point p) {
        //verifica se já existe algum ponto na lista encadeada
       if(start.p == null){
           start.p = p;
           start.next = start;
           return;
       }

       //percorre todos os nós que já estão no ciclo para saber qual é o mais próximo
       Node current = start;
       Node maisProximo = new Node();
       do{
           if(maisProximo.p != null){
               if(p.distanceTo(current.p) < p.distanceTo(maisProximo.p)){
                   maisProximo = current;
               }
           }else{
               maisProximo = current;
           }
           current = current.next;
       }while (!current.equals(start));

       //inserindo o novo ponto no ciclo
       Node t1 = new Node();
       t1.p = p;
       Node t2 = maisProximo.next;
       maisProximo.next = t1;
       t1.next = t2;
    }



    // insere p usando a heurística do menor aumento
    public void insertSmallest(Point p) {
        //Instancia do nó que será inserido entre dois nós existentes
        Node newNode = new Node();
        newNode.p = p;

        //Caso não haja um valor inicial, deve se referenciar o ponto de inicio ao novo nó instanciado
        //para manter a estrutura de ciclo(circular) referencia-se a si mesmo
        if(start.p == null){
            start = newNode;
            start.next = start;
            return;
        }
        //Nó que deve referenciar o nó de menor aumento a ser encontrada, por enquanto aponta pra null
        Node bestPosition = null;
        // custo minimo inicializado com maior valor possível para minimização
        double minCost = Double.POSITIVE_INFINITY;

        Node current = this.start;
        do {
            //nó next referencia o pŕoximo da lista encadeada
            Node next = current.next;
            //armazenamento do menor aumento baseado na ideia da desigualdade triangular(citado por karubbi)
            double increase = current.p.distanceTo(p) + p.distanceTo(next.p) - current.p.distanceTo(next.p);
            if (increase < minCost) {
                minCost = increase;
                bestPosition = current;
            }
            //atualiza nó atual para próx iteração
            current = current.next;
        } while (current != this.start);//ponto de parada

        // Inserir p após o melhor nó encontrado
        newNode.next = bestPosition.next;
        bestPosition.next = newNode;

    }

    // testa esta classe chamando todos os construtores e métodos de instância
    public static void main(String[] args) {
        // define 4 pontos, vértices de um quadrado
        Point a = new Point(1.0, 1.0);
        Point b = new Point(1.0, 4.0);
        Point c = new Point(4.0, 4.0);
        Point d = new Point(4.0, 1.0);

        // cria o ciclo a -> b -> c -> d -> a
        Tour squareTour = new Tour(a, b, c, d);

        // imprime o número de pontos na saída padrão
        int size = squareTour.size();
        StdOut.println("# de pontos = " + size);

        // imprime o comprimento do ciclo na saída padrão
        double length = squareTour.length();
        StdOut.println("Comprimento do ciclo = " + length);

        // imprime o ciclo na saída padrão
        StdOut.println(squareTour);

        /*StdDraw.setXscale(0, 6);
        StdDraw.setYscale(0, 6);

        Point e = new Point(5.0, 6.0);
<<<<<<< HEAD
        squareTour.insertNearest(e);
        //squareTour.insertSmallest(e);
=======
        //squareTour.insertNearest(e);
        squareTour.insertSmallest(e);
        StdOut.println("Comprimento depois: " + squareTour.length());
        StdOut.println("Tour atualizado:\n" + squareTour);
>>>>>>> ad3758b1166fb74023e746f78871bbd39f30f384
        squareTour.draw();

         */
    }
}

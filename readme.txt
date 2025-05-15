*****************************************************************************************************
T290 - Resolução de Problemas com Grafos
Orientador: Prof. Me Ricardo Carubbi
*****************************************************************************************************

Trabalho 2: Problema do Caixeiro Viajante

Horas para completar o trabalho (opcional):

Q1. Explique como você implementou a heurística de inserção pelo vizinho mais próximo.
Comente também como você atualiza as ligações da lista encadeada circular após inserir o novo ponto.

Resposta:
A heuristica do vizinho mais próximo foi implementada da seguinte forma:
-O ponto "p" é passado para a função "insertNearest"
-A função verifica se já existe algum ponto na lista encadeada circular
-Caso não exista nenhum ponto, o ponto "p" vai ser o primeiro
-Caso já exista algum ponto na lista encadeada, a função percorre todos os pontos da lista
e verifica qual está mais próximo do ponto p (de acordo com a distância euclidiana).
Após obter o ponto mais próximo, o ponto p é adicionado na lista encadeada como "next"
desse ponto.



Q2. Explique como você implementou a heurística de menor aumento.
Liste apenas as diferenças em relação à heurística de inserção pelo vizinho mais próximo.

Resposta:
Etapas de funcionamento do smallest insertion
- Cria-se o novo nó newNode com o ponto p que será inserido.
- Se o ciclo está vazio (start.p == null), o novo nó vira o único ponto no ciclo e referencia a si mesmo (start.next = start), formando o ciclo.
- Inicialização de bestPosition para guardar o nó após o qual será feita a inserção
- Inicialização de minCost que começa com valor infinito para garantir que o primeiro aumento encontrado será menor.
- Percorre-se todos os nós no ciclo atual usando um loop do-while que passa por todos os pontos circularmente até voltar ao início.
- Para cada par de nós consecutivos calcula-se o aumento no comprimento do ciclo se o ponto p for inserido entre esses dois nós.
- A fórmula do custo é a seguinte: increase=d(current,p)+d(p,next)−d(current,next)
- Se o aumento calculado for menor que minCost, atualiza-se minCost e marca bestPosition como o nó current.
- Depois de testar todas as posições possíveis, insere o novo nó após bestPosition.

Diferenças de funcionamento das implementações
- Vizinho mais próximo: insere o novo ponto após o ponto já no ciclo que está mais perto dele (menor distância direta).

- Menor aumento: insere o novo ponto entre dois pontos consecutivos do ciclo que causam o menor aumento possível no comprimento total da rota.




Q3. Explique por que é melhor usar uma lista encadeada circular em vez de um vetor.
Considere a complexidade das operações de inserção e remoção de pontos.

Resposta:
Em um vetor a complexidade de inserção de um ponto é O(n), já que para inserir um ponto
entre 2 pontos que já existem, teria que realocar todos os outros pontos presentes em um dos lados do vetor.
Já na lista encadeada, o tempo de inserção e remoção é constante.


Q4. Preencha os comprimentos calculados pelas suas heurísticas.

| Arquivo de dados | Vizinho mais próximo | Menor aumento |
| ---------------- | -------------------- | ------------- |
| tsp10.txt        | 1566.1363            | 1655.7462     |
| tsp100.txt       | 7389.9297            | 4887.2190     |
| tsp1000.txt      | 27868.7106           | 17265.6281    |
| usa13509.txt     | 77449.9794           | 45074.7769    |

Q5. Realize duas análises de tempo

Comente se o valor de \(b\) encontrado é coerente com a análise teórica da complexidade de seu algoritmo.

- Estime o tempo de execução (em segundos) de cada heurística como uma função de \(n\), usando expressões da forma: \(a \times n^b\), onde \(b\) é um inteiro.
- Explique como você determinou cada uma das respostas.
- Para obter seus pontos de dados, execute as duas heurísticas para \(n = 1000\) e dobre \(n\) repetidamente até que o tempo de execução ultrapasse 60 segundos.
- Você pode usar o **TSPTimer** para ajudar, conforme indicado na lista de verificação.
- Se usar, execute com a opção **-Xint** para desativar otimizações do compilador.
- Se para \(n = 1000\) o tempo já ultrapassar 60 segundos, seu código está muito lento. Veja a lista de verificação para sugestões de correção.

| n         | Tempo vizinho mais próximo (s) | Tempo menor aumento (s) |
| ----      | ------------------------------ | ----------------------- |
| 10000     | 0.523                          | 0.595                   |
| 20000     | 2.216                          | 2.361                   |
| 40000     | 9.067                          | 9.335                   |
| 80000     | 57.385                         | 43.2                    |
| 160000    | 500.77                         | 180.323                 |

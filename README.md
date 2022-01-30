**INTRODUÇÃO**
<br>Este sistema *Movie-Manager* está em desenvolvimento, seu objetivo principal é a prática de alguns conteúdos aprendidos em 2021:
<br><br>- Java
<br> - Hibernate
<br> - SpringBoot
<br> - PostgreSQL
<br> - loading...
<br><br>**DOCUMENTAÇÃO**
<br>Problema: Meu cliente quer abrir um cinema e precisa de um sistema que gerencie as salas, filmes em cartaz e venda de ingressos.
<br><br>**Histórias de Usuário**
<br>Criei essas histórias de usuário para que, a partir delas, conseguisse criar o sistema.
<br><br>*Como administrador, quero:*
<br> - Cadastrar uma sala de cinema informando características como: nome, quantidade de poltronas, tipos de filme suportados, poltronas vip.
<br> - Cadastrar um novo perfil de usuário para acesso, informando: nome, login e senha.
<br> - Cadastrar um filme informando: título, sinopse, categoria, faixa etária, idioma, duração.
<br> - Vincular um filme a um cartaz informando: a data, a hora, e sala.
<br> - Cadastrar as opções de pagamento.
<br> - Cadastrar os dias da semana que recebem descontos fixos.
<br> - Cadastrar tipos de desconto, exemplo: estudantes, idosos e crianças e a respectiva porcentagem.
<br> - Cadastrar um valor padrão para o ingresso.
<br> - Cadastrar categoria para setar no cadastro do filme.
<br><br>*Como atendente de vendas, quero:*
<br>- Gerar uma venda informando: cartaz, poltrona, cpf do cliente, valor do ingresso, valor do pagamento, desconto aplicado (opcional), data da venda.
<br>- Imprimir o ingresso para o cliente.
<br><br>**Projetando o sistema**
<br>A partir das histórias de usuário, inicio a modelagem do banco de dados:
<br><br>*Entidades*

```sql
Table: ROOM

Description: A sala possui a informação 'nome' para orientar 
quanto a localização onde ocorrerá o filme. 

+-------+---------+------+-----+
| Field | Type    | Null | Key |
+-------+---------+------+-----+
| id    | Integer | NO   | PRI |
| name  | String  | NO   |     |
+-------+---------+------+-----+
```

```sql
Table: PAYMENT

Description: O pagamento possui 'descrição' para ser inserido no comprovante
da compra do ingresso.

+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+-----+------+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
+-------------+---------+-----+------+
```

```sql
Table: LANGUAGE

Description: A linguagem possui 'descrição' para que o cliente seja informado 
quanto ao idioma em que será exibido o filme.

+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+-----+------+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
+-------------+---------+-----+------+
```

```sql
Table: TICKET PRICE

Description: O 'price' do ticket serve para ser considerado em cada venda. Sempre
será considerado o preço que não possui data 'finished', pois isso significa que o 
preço está vigente. Quando o preço é alterado, o preço vigente recebe nova data 
'finished' e o novo preço recebe a data de alteração no campo 'created'.

+----------+-----------+------+-----+
| Field    | Type      | Null | Key |
+----------+-----------+------+-----+
| id       | Integer   | NO   | PRI |
| price    | Double    | NO   |     |
| created  | LocalDate | NO   |     |
| finished | LocalDate | YES   |     |
+----------+-----------+---- -+-----+
```

```sql
Table: MOVIE

Description: O filme possui características de 'title', 'synopsis', 'category',
'minimumAgeRequired', 'language', 'durationMinutes', 'type' e 'isOnActivePoster'.

+--------------------+-----------+------+-----+
| Field              | Type      | Null | Key |
+--------------------+-----------+------+-----+
| id                 | Integer   | NO   | PRI |
| title              | String    | NO   |     |
| synopsis           | String    | NO   |     |
| category           | Category  | NO   |     |
| minimumAgeRequired | Integer   | NO   |     |
| language           | Language  | NO   |     |
| durationMinutes    | Language  | NO   |     |
| type               | MovieType | NO   |     |
| isOnActivePoster   | Boolean   | NO   |     |
+--------------------+-----------+---- -+-----+
```

```sql
Table: DISCOUNT

Description: O desconto possui uma 'description' para que o usuário entenda em que 
ocasião ele pode ser aplicado, e uma 'percentage' que será utilizada para reduzir
o preço do ingresso de um filme.

+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+------+-----+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
| percentage  | Double  | NO   |     |
+-------------+---------+------+-----+
```

```sql
Table: CATEGORY

Description: A categoria possui 'description' para descrever o tipo da história do 
filme, podendo ser inserida no cartaz a fim de trazer mais detalhes sobre o 
filme para o cliente.

+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+------+-----+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
+-------------+---------+------+-----+
```

```sql
Table: DAY OF WEEK DISCOUNT

Description: se refere a um desconto fixo para 'dayOfWeek'. A tabela recebe
uma carga inicial com todos os dias da semana cadastrados recebendo 'percentage'
igual a zero. Sempre que um ingresso é vendido, sera aplicado à ele uma porcentagem
de desconto. Caso não exista nenhum desconto aplicado na venda, vale o 'DayOfWeekDiscount'. 
Caso exista um 'Discount' aplicado, vale aquele que tiver maior porcentagem, nunca 
a soma de ambos.

+------------+---------+------+-----+
| Field      | Type    | Null | Key |
+------------+---------+------+-----+
| id         | Integer | NO   | PRI |
| dayOfWeek  | String  | NO   |     |
| isActive   | Boolean | NO   |     |
| percentage | Double  | NO   |     |
+------------+---------+------+-----+
```

**ROOM** (sala de cinema), **ROW** (fileira de uma sala) e **ARMCHAIR** (poltrona de uma fileira)

Cadastramos uma sala de cinema, informamos quantas fileiras
existem e a quantidade de poltronas por fileira.
Com essas informações a sala é criada, possuindo uma quantidade de fileiras 
com suas respectivas poltronas.

Após essa construção inicial da sala, é possível alterar a quantidade 
de cadeiras de uma determinada fileira, determinando assim a planta exata
da sala de cinema. 

```sql 
Room
+-------+---------+------+-----+
| Field | Type    | Null | Key |
+-------+---------+------+-----+
| id    | Integer | NO   | PRI |
| name  | String  | NO   |     |
+-------+---------+------+-----+

Row
+---------+---------+------+-----+
| Field   | Type    | Null | Key |
+---------+---------+------+-----+
| id      | Integer | NO   | PRI |
| letter  | String  | NO   |     |
+---------+---------+------+-----+

Armchair
+-----------+---------+------+-----+
| Field     | Type    | Null | Key |
+-----------+---------+------+-----+
| id        | Integer | NO   | PRI |
| number    | Integer | NO   |     |
| available | boolean | NO   |     |
+-----------+---------+------+-----+
```

<br>**LANGUAGE** (idioma)

Utilizada para determinas o idioma de um filme.

```sql
Language
+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+-----+------+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
+-------------+---------+-----+------+
```

<br>**CATEGORY** (categoria de filme)

A categoria é um atributo do filme que informa o tipo do conteúdo
apresentado.

```sql
Category
+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+------+-----+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
+-------------+---------+------+-----+
```

<br>**MOVIE** (filme)

O filme possui características de 'title', 'synopsis', 'category',
'minimumAgeRequired', 'language', 'durationMinutes', 'type' e 'isOnActivePoster'
que serão exibidas no cartaz quando este filme estiver disponível em uma sessão.

```sql
Movie
+--------------------+-----------+------+-----+
| Field              | Type      | Null | Key |
+--------------------+-----------+------+-----+
| id                 | Integer   | NO   | PRI |
| title              | String    | NO   |     |
| synopsis           | String    | NO   |     |
| category           | Category  | NO   |     |
| minimumAgeRequired | Integer   | NO   |     |
| language           | Language  | NO   |     |
| durationMinutes    | Long      | NO   |     |
| type               | MovieType | NO   |     |
+--------------------+-----------+---- -+-----+
```

<br>**PAYMENT** (pagamento)

Utilizado para cadastrar formas de pagamento que serão informados
na emissão de um recibo da compra de um ingresso.

```sql
Payment
+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+-----+------+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
+-------------+---------+-----+------+
```

<br>**TICKET PRICE** (preço de um ingresso)

O TicketPrice serve para mantermos um valor padrão da venda de ingresso.
O valor dele sempre será utilizado como base e, de acordo com o desconto 
aplicado, termos o preço final da venda.

Sempre será considerado o TicketPrice que não possui data 'finished', pois
isso significa que este é o preço vigente a ser utilizado como base para o
cálculo da venda. 
Quando informamos um novo preço a ser considerado, o preço vigente recebe 
uma data 'finished' e o novo preço recebe a data 'created', tornando-se o 
preço vigente.

```sql
TicketPrice
+----------+-----------+------+-----+
| Field    | Type      | Null | Key |
+----------+-----------+------+-----+
| id       | Integer   | NO   | PRI |
| price    | Double    | NO   |     |
| created  | LocalDate | NO   |     |
| finished | LocalDate | YES   |     |
+----------+-----------+---- -+-----+
```

<br>**DISCOUNT** (desconto)

O desconto possui uma 'description' para que o usuário entenda em que ocasião 
ele pode ser aplicado, e uma 'percentage' que será utilizada para reduzir o 
preço do ingresso de um filme.

Se o desconto estiver desativado ele não poderá ser aplicado à uma venda.

```sql
Discount
+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+------+-----+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
| percentage  | Double  | NO   |     |
| active      | boolean  | NO   |     |
+-------------+---------+------+-----+
```

<br>**DAY OF WEEK DISCOUNT** (desconto de dia da semana)

Esse desconto se refere a um desconto fixo para 'dayOfWeek'. A tabela recebe
uma carga inicial com todos os dias da semana cadastrados recebendo 'percentage'
igual a zero, podendo o usuário setar qual o desconto padrão que cada dia da 
semana possui, se possuir. 

Sempre que um ingresso é vendido, será aplicado à ele uma porcentagem de desconto. 
Caso não exista nenhum desconto aplicado na venda, vale o 'DayOfWeekDiscount'.
 
Caso exista um 'Discount' aplicado, vale aquele que tiver maior porcentagem, nunca 
a soma de ambos.

```sql
DayOfWeekDiscount
+------------+---------+------+-----+
| Field      | Type    | Null | Key |
+------------+---------+------+-----+
| id         | Integer | NO   | PRI |
| dayOfWeek  | String  | NO   |     |
| isActive   | Boolean | NO   |     |
| percentage | Double  | NO   |     |
+------------+---------+------+-----+
```

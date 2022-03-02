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
| letter    | String  | NO   |     |
| number    | Integer | NO   |     |
| available | boolean | NO   |     |
+-----------+---------+------+-----+
```

<br>**LANGUAGE** (idioma)

Utilizada para determinar o idioma de um filme.

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

Utilizado para dscrever a forma de pagamento adotada durante a compra
de um ingresso.

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
aplicado, obtemos o preço final da venda.

Sempre será considerado o TicketPrice que não possui data 'finished', pois
isso significa que este é o preço vigente a ser utilizado como base para o
cálculo da venda. 
Quando informamos um novo TicketPrice, o preço que está vigente no momento
recebe uma data 'finished' para que o TicketPrice que estamos cadastrando se
torne o preço vigente.

```sql
TicketPrice
+----------+-----------+------+-----+
| Field    | Type      | Null | Key |
+----------+-----------+------+-----+
| id       | Integer   | NO   | PRI |
| price    | Double    | NO   |     |
| created  | LocalDate | NO   |     |
| finished | LocalDate | YES  |     |
+----------+-----------+---- -+-----+
```

<br>**DISCOUNT** (desconto)

O desconto é um benefício que pode ser utilizado para diminuir o valor de 
um ingresso, ele é cadastrado pelo usuário e pode estar ou não ativado, por
exemplo: desconto de dia das crianças.

Como existem dois tipos de descontos no sistema (dayOfWeekDiscount e discount),
vale o que tiver uma porcentagem maior. Não é permitido aplicar mais do que um
desconto.

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

Esse desconto é fixo e temos um para cada dia da semana. O sistema inicia com
uma carga inicial com todos os dias da semana cadastrados recebendo porcentagem
igual a zero, podendo o usuário setar qual o desconto que cada dia da semana 
possuirá, caso desejado. 

Como existem dois tipos de descontos no sistema (dayOfWeekDiscount e discount), 
vale o que tiver uma porcentagem maior. Não é permitido aplicar mais do que um
desconto.

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

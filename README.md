Movie-Manager (Gerenciador de cinema)

**DETALHAMENTO DO SISTEMA**:

**ROOM** (sala de cinema) e **ARMCHAIR_MODEL** (poltronas da sala de cinema)

Cadastramos uma sala de cinema, informamos quantas fileiras existem e a quantidade 
de poltronas por fileira. Com essas informações pré cadastradas, nossa sala e suas 
respectivas poltronas são criadas para que o cliente escolha onde ele irá sentar para 
assistir o filme desejado.

Após essa construção inicial da sala, é possível alterar a quantidade de cadeiras 
de uma determinada fileira, determinando assim a planta exata da sala de cinema.

A entidade armchairModel é utilizada como um modelo para criar as poltronas de uma
session.

```sql 
Room
+-------+---------+------+-----+
| Field | Type    | Null | Key |
+-------+---------+------+-----+
| id    | Integer | NO   | PRI |
| name  | String  | NO   |     |
+-------+---------+------+-----+

Armchair model
+-----------+---------+------+-----+
| Field     | Type    | Null | Key |
+-----------+---------+------+-----+
| id        | Integer | NO   | PRI |
| room_id   | Integer | NO   |     |
| letter    | String  | NO   |     |
| number    | Integer | NO   |     |
+-----------+---------+------+-----+

API disponível:

- Listar salas
- Consultar sala por nome
- Salvar sala e poltronas
- Consultar as poltronas de uma sala de cinema por id
- Alterar a quantidade de poltronas da fileira de uma sala de cinema

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

API disponível:

- Listar
- Consultar por nome
- Salvar
- Alterar
- Deletar por id

```

<br>**CATEGORY** (categoria de filme)

A categoria é um atributo do filme que informa o tipo do conteúdo apresentado.

```sql
Category
+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+------+-----+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
+-------------+---------+------+-----+

API disponível:

- Listar
- Consultar por nome
- Salvar
- Alterar
- Deletar por id

```

<br>**MOVIE** (filme)

O filme possui características de 'title', 'synopsis', 'category',
'minimumAgeRequired', 'language', 'durationMinutes' e 'type' que serão exibidas no cartaz quando este filme estiver disponível em uma sessão.

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

API disponível:

- Listar
- Consultar por título
- Salvar
- Alterar
- Deletar por id

```

<br>**PAYMENT** (pagamento)

Utilizado para descrever a forma de pagamento adotada durante a compra de um 
ingresso.

```sql
Payment
+-------------+---------+------+-----+
| Field       | Type    | Null | Key |
+-------------+---------+-----+------+
| id          | Integer | NO   | PRI |
| description | String  | NO   |     |
+-------------+---------+-----+------+

API disponível:

- Listar
- Consultar por descrição
- Salvar
- Alterar
- Deletar por id

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

API disponível:

- Listar
- Consultar preço vigente
- Salvar novo preço vigente

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
| active      | boolean | NO   |     |
+-------------+---------+------+-----+

API disponível:

- Listar
- Consultar por descrição
- Consultar descontos ativos
- Salvar
- Alterar
- Deletar por id

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
| active     | Boolean | NO   |     |
| percentage | Double  | NO   |     |
+------------+---------+------+-----+

API disponível:

- Listar
- Alterar

```

<br>**SESSION** (sessão de filme) e **ARMCHAIR** (poltrona de uma sessão de filme)

A sessão é um cartaz que informa ao cliente quando um determinado filme será apresentado,
bem como dia, horário, sala, demais detalhes do filme e as poltronas disponíveis.

Quando um cliente compra um ingresso para ver uma sessão de filme, este poderá escolher uma
poltrona que está disponível, e quando o faz, esta passa a ficar indisponível.

```sql
Session
+------------+---------------+------+-----+
| Field      | Type          | Null | Key |
+------------+---------------+------+-----+
| id         | Integer       | NO   | PRI |
| movie      | Movie         | NO   |     |
| dateTime   | LocalDateTime | NO   |     |
| room       | Room          | NO   |     |
+------------+---------------+------+-----+

Armchair
+--------------+---------+------+-----+
| Field        | Type    | Null | Key |
+--------------+---------+------+-----+
| id           | Integer | NO   | PRI |
| sessionId    | Integer | NO   |     |
| letterNumber | String  | NO   |     |
| available    | boolean | NO   |     |
+--------------+---------+------+-----+

API disponível:

- Consultar as poltronas de uma sessão
- Salvar
- Reservar uma poltrona
- Liberar uma poltrona

```
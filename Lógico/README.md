# Modelo Lógico TheCuckoo 
## Introdução
Esta pasta contém o projeto lógico do banco de dados de uma rede social (TheCuckoo).

#### Conteúdo da pasta
[logico.sql - Script SQL do Banco de dados do projeto](https://raw.githubusercontent.com/psmousinho/TheCuckoo/master/Lógico/logico.sql)<br>

## Modelo conceitual à ser traduzido
O modelo a ser traduzido está descrito [aqui](https://github.com/psmousinho/TheCuckoo/tree/master/Conceitual) e tem como diagrama ER o seguinte esquema:

<p align="center">
 <img src="https://raw.githubusercontent.com/psmousinho/TheCuckoo/master/Conceitual/Diagrama.png">
 </p>
 
## Tabelas Criadas
### UserProfile
Essa tabela engloba as entidades Usuário e Perfil pois essas entidades estão relacionadas com cardinalidade 1:1, logo foi optado por modelar o relacionamento por meio de fusão de tabelas.

> userprofile ( <ins>login</ins>, passw, passhash, visibility, bio, realname ) <br>

### UserRel
Essa tabela representa o auto relacionamento n:n entre usuários, foi optado criar uma tabela própria para este relacionamento.

> userrel ( <ins>srcuser</ins>, <ins>tgtuser</ins>, datestamp, status ) <br>
>>  srcuser referencia userprofile <br>
>>   tgtuser referencia userprofile <br>

### Post
Essa tabela representa a entidade Postagem e o relacionamento 1:1 entre Postagem e Perfil (e por consequência usuário), o relacionamento foi modelado a partir da adição da coluna author.

> post ( <ins>author</ins>, <ins>datestamp</ins>, ptext, foto ) <br>
>>   author referencia userprofile <br>

### Commnt
Essa tabela representa a entidade Comentario e o relacionamento 1:n entre Comentario e Postagem, que foi decidido ser modelado por meio de adição de colunas, e o relacionamento 1:1 entre Comentario e Usuario, modelado também por adição de coluna (author).

> commnt ( <ins>author</ins>, <ins>pauthor</ins>, <ins>pdate</ins>, <ins>datestamp</ins>, ctext ) <br>
>>   author referencia userprofile <br>
>>   (pauthor, pdate) referencia post <br>
 
### Topic
Essa tabela representa a entidade tópico e o relacionamento de tópico com comentário e o relacionamento de tópico com postagem, ambas as referências foram modeladas com adição de colunas.

> topic ( <ins>tname</ins>, datestamp, pauthor, pdate, cauthor, cpauthor, cpdate, cdate ) <br>
>>   (pauthor, pdate) referencia post <br>
>>  (cauthor, cpauthor, cpdate, cdate) referencia commnt <br>

### TagPostUser
Essa tabela representa o relacionamento de marcação entre postagem e usuário, por se tratar de um relacionamento n:n foi criada uma tabela própria para tal.

> tagpostuser ( <ins>pauthor</ins>, <ins>pdate</ins>, <ins>taguser</ins> ) <br>
>>   (pauthor, pdate) referencia post <br>
>>   taguser referencia userprofile <br>

### TagCommntUser
Essa tabela representa o relacionamento de marcação entre comentário e usuário, pelo mesmo motivo de marcação de usuário em postagens, este relaciomento foi modelado como tabela própria.

> tagcommntuser ( <ins>cauthor</ins>, <ins>cdate</ins>, <ins>cpauthor</ins>, <ins>cpdate</ins>, <ins>taguser</ins> ) <br>
>>  (cauthor, cpauthor, cpdate, cdate) referencia commnt <br>
>>  taguser referencia userprofile <br>

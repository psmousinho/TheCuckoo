# Modelo Conceitual TheCuckoo 
## Introdução
Esta pasta contém o projeto conceitual de uma rede social (TheCuckoo) utilizando a abordagem de modelagem Entidade-Relacionamento.

#### Conteúdo da pasta
> Diagrama ER (Projeto.brM3)<br>
> brModelo.jar (para abrir o Projeto.brM3, necessário Java 8 ou superior)

## Entidades
### Usuário
Um usuário possui um nome de usuário que segue uma regra de composição e também é único, logo modelamos este atributo como identificador da entidade, além disso para fazer login é necessário uma senha para o usuário logo também foi adicionado o atributo senha para esta entidade.

### Perfil
O perfil contém como atributos a visibilidade, nome real do usuário e sua biografia, ele também deve apresentar o nome de usuário, postagens, seguidores e quem ele segue. Porém isso será resolvido com os relacionamentos entre perfil e outras entidades (Ver adiante).

### Postagem
Uma postagem contém uma data e hora, um texto e (opcionalmente) uma foto como atributos.

### Comentário
Um comentário contém uma data e hora e também um texto como atributos.

### Tópico
Cada tópico possui um nome que segue as mesmas regras de composição do nome de usuário, logo modelamos este atributo como identificador da entidade. Além disso há um atributo data e hora para a entidade tópico.

## Relacionamentos
### Usuário <-> Usuário (Relacionamento)
Cada usuário pode seguir e bloquear outros usuários, decidimos modelar isso como um auto relacionamento da entidade usuário, para distinguir entre seguimento (e também se a requesição foi aceita, caso o perfil seja privado) e bloqueio, foi criado um atributo status. Por fim, adicionamos um atributo data e hora para efeitos de obter notificações a partir de consultas. Além disso a cardinalidade é n:n pois um usuário pode se relacionar com vários outros usuários.

### Usuário <-> Perfil
Cada usuário possui um perfil, por isso o relacionamento é 1:1.

### Usuário <-> Postagem (Marcação Post.)
Uma postagem pode marcar vários usuários, por isso o relaciomento é n:n.

### Usuário <-> Comentário (Marcação Coment.)
Um comentário pode conter várias marcações de usuários, pelo mesmo motivo de marcação post a cardinalidade é n:n.

### Usuário <-> Comentário (Autoria)
Um comentário possui um autor que não necessariamente é o autor da postagem a qual o comentário se relaciona, para isso criamos um relacionamento para poder identificar quem é o autor do comentário, a cardinalidade neste caso é 1:n, pois um usuário pode fazer vários comentários.

### Comentário <-> Tópico
Um comentario pode possuir vários topicos e cada topico pode estar associado com vários comentários. Portanto a cardinalidade desse relacionamento é n:n.

### Comentário <-> Postagem
Cada comentário deve estar associado a uma Postagem, além disso uma postagem pode estar associado a diversos comentários. Portanto a cardinalidade desse relacionamneto é n:1.

### Postagem <-> Perfil
Uma postagem deve estar associa a um Perfil, porem um Perfil pode estar associado a diversas postagens. Portanto esse relacionamneto é n:1.

## Diagrama 
Por fim, o diagrama ER do banco de dados do projeto:
 <p align="center">
 <img src="https://raw.githubusercontent.com/psmousinho/TheCuckoo/master/Conceitual/Diagrama.png">
 </p>

# Modelo Logico TheCuckoo 
## Introdução
Esta pasta contém o projeto logico o banco de dados de uma rede social (TheCuckoo).

#### Conteúdo da pasta


## Modelo conceitual à ser traduzido
<p align="center">
 <img src="https://raw.githubusercontent.com/psmousinho/TheCuckoo/master/Conceitual/Diagrama.png">
 </p>
 Mais esclarecimento em https://raw.githubusercontent.com/psmousinho/TheCuckoo/master/Conceitual/
 
## Tabelas Criadas
### UserProfile
Essa tabela engloba as entidades Usuario e Perfil posi essas entidades estao ralacionados com cardinalidade 1:1.

userprofile ( <ins>login</ins>, passw, passhash, visibility, bio, realname )


### UserRel
Essa tabela representa o auto relacionamento n:n entre usuarios.

userrel ( <ins>srcuser</ins>, <ins>tgtuser</ins>, datestamp, status )

   srcuser referencia userprofile
   
   tgtuser referencia userprofile

### Post
Essa tabela representa a entidade postagem e o relacionamento 1:1 entre postagem e perfil(e por consequencia usuario).

post ( <ins>author</ins>, <ins>datestamp</ins>, ptext, foto )

   author referencia userprofile

### Commnt
Essa tabela representa a entidade comentario e o relacionamento 1:1 entre comentario e postagem e o relacionamneto 1:1 entre comentario e usuario.

commnt ( <ins>author</ins>, <ins>pauthor</ins>, <ins>pdate</ins>, <ins>datestamp</ins>, ctext )

   (pauthor, pdate) referencia post
 
### Topic
Essa tabela representa a entidade topico e o relacionamento de topico com comentario e o relacionamento de topico com postagem.

topic ( <ins>tname</ins>, datestamp, pauthor, pdate, cauthor, cpauthor, cpdate, cdate )

   (pauthor, pdate) referencia post
   
   (cauthor, cpauthor, cpdate, cdate) referencia commnt

### TagPostUser
Essa tabela representa o relacionamento de marcação entre postagem e usuario.

tagpostuser ( <ins>pauthor</ins>, <ins>pdate</ins>, <ins>taguser</ins> )

   (pauthor, pdate) referencia post 
   
   taguser referencia userprofile

### TagCommntUser
Essa tabela representa o relacionamneto de marcação entre comentario e usuario.
tagcommntuser ( <ins>cauthor</ins>, <ins>cdate</ins>, <ins>cpauthor</ins>, <ins>cpdate</ins>, <ins>taguser</ins> )

   (cauthor, cpauthor, cpdate, cdate) referencia commnt
   
   taguser referencia userprofile


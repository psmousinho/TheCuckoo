# Modelo Logico TheCuckoo 
## Introdução
Esta pasta contém o projeto logico o banco de dados de uma rede social (TheCuckoo).

#### Conteúdo da pasta


## Modelo conceitual à ser traduzido
<p align="center">
 <img src="https://raw.githubusercontent.com/psmousinho/TheCuckoo/master/Conceitual/Diagrama.png">
 </p>
 Mais esclarecimento em https://raw.githubusercontent.com/psmousinho/TheCuckoo/master/Conceitual/
 
##Tabelas Criadas
### UserProfile
Essa tabela engloba as entidades Usuario e Perfil posi essas entidades estao ralacionados com cardinalidade 1:1.
'''CREATE TABLE public.userprofile (
    login character varying(31) NOT NULL,
    passw character varying(255) NOT NULL,
    passhash character varying(255) DEFAULT '-1'::character varying,
    visibility boolean DEFAULT false NOT NULL,
    bio character varying(4095) DEFAULT 'Hello i am new to /cuckoo!!'::character varying NOT NULL,
    realname character varying(255) NOT NULL
);'''

### UserRel
Essa tabela representa o auto relacionamento n:n entre usuarios.
'''CREATE TABLE public.userrel (
    srcuser character varying(31) NOT NULL,
    tgtuser character varying(31) NOT NULL,
    datestamp timestamp without time zone NOT NULL,
    status smallint NOT NULL
);'''

### Post
Essa tabela representa a entidade postagem e o relacionamento 1:1 entre postagem e perfil(e por consequencia usuario).
'''CREATE TABLE public.post (
    author character varying(31) NOT NULL,
    datestamp timestamp without time zone NOT NULL,
    ptext character varying(255) NOT NULL,
    foto character varying(1023)
);'''

### Commnt
Essa tabela representa a entidade comentario e o relacionamento 1:1 entre comentario e postagem e o relacionamneto 1:1 entre comentario e usuario.
'''CREATE TABLE public.commnt (
    author character varying(31) NOT NULL,
    pauthor character varying(31) NOT NULL,
    pdate timestamp without time zone NOT NULL,
    datestamp timestamp without time zone NOT NULL,
    ctext character varying(255)
);'''

### Topic
Essa tabela representa a entidade topico e o relacionamento de topico com comentario e o relacionamento de topico com postagem.
'''CREATE TABLE public.topic (
    tname character varying(31) NOT NULL,
    datestamp timestamp without time zone,
    pauthor character varying(31),
    pdate timestamp without time zone,
    cauthor character varying(31),
    cpauthor character varying(31),
    cpdate timestamp without time zone,
    cdate timestamp without time zone
);'''

### TagPostUser
Essa tabela representa o relacionamento de marcação entre postagem e usuario.
'''CREATE TABLE public.tagpostuser (
    pauthor character varying(31) NOT NULL,
    pdate timestamp without time zone NOT NULL,
    taguser character varying(31) NOT NULL
);'''

### TagCommntUser
Essa tabela representa o relacionamneto de marcação entre comentario e usuario.
'''CREATE TABLE public.tagcommntuser (
    cauthor character varying(31) NOT NULL,
    cdate timestamp without time zone NOT NULL,
    cpauthor character varying(31),
    cpdate timestamp without time zone,
    taguser character varying(31) NOT NULL
);'''



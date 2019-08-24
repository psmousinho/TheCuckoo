--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.15
-- Dumped by pg_dump version 9.6.15

-- Started on 2019-08-22 09:51:00

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 188 (class 1259 OID 24667)
-- Name: commnt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.commnt (
    author character varying(31) NOT NULL,
    pauthor character varying(31) NOT NULL,
    pdate timestamp without time zone NOT NULL,
    datestamp timestamp without time zone NOT NULL,
    ctext character varying(255)
);


ALTER TABLE public.commnt OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 24639)
-- Name: post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post (
    author character varying(31) NOT NULL,
    datestamp timestamp without time zone NOT NULL,
    ptext character varying(255) NOT NULL,
    foto character varying(1023)
);


ALTER TABLE public.post OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 24722)
-- Name: tagcommntuser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tagcommntuser (
    cauthor character varying(31) NOT NULL,
    cdate timestamp without time zone NOT NULL,
    cpauthor character varying(31),
    cpdate timestamp without time zone,
    taguser character varying(31) NOT NULL
);


ALTER TABLE public.tagcommntuser OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 24707)
-- Name: tagpostuser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tagpostuser (
    pauthor character varying(31) NOT NULL,
    pdate timestamp without time zone NOT NULL,
    taguser character varying(31) NOT NULL
);


ALTER TABLE public.tagpostuser OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 24692)
-- Name: topic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topic (
    tname character varying(31) NOT NULL,
    datestamp timestamp without time zone,
    pauthor character varying(31),
    pdate timestamp without time zone,
    cauthor character varying(31),
    cpauthor character varying(31),
    cpdate timestamp without time zone,
    cdate timestamp without time zone
);


ALTER TABLE public.topic OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 24628)
-- Name: userprofile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userprofile (
    login character varying(31) NOT NULL,
    passw character varying(255) NOT NULL,
    passhash character varying(255) DEFAULT '-1'::character varying,
    visibility boolean DEFAULT false NOT NULL,
    bio character varying(4095) DEFAULT 'Hello i am new to /cuckoo!!'::character varying NOT NULL,
    realname character varying(255) NOT NULL
);


ALTER TABLE public.userprofile OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 24652)
-- Name: userrel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userrel (
    srcuser character varying(31) NOT NULL,
    tgtuser character varying(31) NOT NULL,
    datestamp timestamp without time zone NOT NULL,
    status smallint NOT NULL
);


ALTER TABLE public.userrel OWNER TO postgres;

--
-- TOC entry 2173 (class 0 OID 24667)
-- Dependencies: 188
-- Data for Name: commnt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.commnt (author, pauthor, pdate, datestamp, ctext) FROM stdin;
\.


--
-- TOC entry 2171 (class 0 OID 24639)
-- Dependencies: 186
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.post (author, datestamp, ptext, foto) FROM stdin;
\.


--
-- TOC entry 2176 (class 0 OID 24722)
-- Dependencies: 191
-- Data for Name: tagcommntuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tagcommntuser (cauthor, cdate, cpauthor, cpdate, taguser) FROM stdin;
\.


--
-- TOC entry 2175 (class 0 OID 24707)
-- Dependencies: 190
-- Data for Name: tagpostuser; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tagpostuser (pauthor, pdate, taguser) FROM stdin;
\.


--
-- TOC entry 2174 (class 0 OID 24692)
-- Dependencies: 189
-- Data for Name: topic; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.topic (tname, datestamp, pauthor, pdate, cauthor, cpauthor, cpdate, cdate) FROM stdin;
\.


--
-- TOC entry 2170 (class 0 OID 24628)
-- Dependencies: 185
-- Data for Name: userprofile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.userprofile (login, passw, passhash, visibility, bio, realname) FROM stdin;
\.


--
-- TOC entry 2172 (class 0 OID 24652)
-- Dependencies: 187
-- Data for Name: userrel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.userrel (srcuser, tgtuser, datestamp, status) FROM stdin;
\.


--
-- TOC entry 2035 (class 2606 OID 24671)
-- Name: commnt commnt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_pkey PRIMARY KEY (author, pauthor, pdate, datestamp);


--
-- TOC entry 2031 (class 2606 OID 24646)
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (author, datestamp);


--
-- TOC entry 2041 (class 2606 OID 24726)
-- Name: tagcommntuser tagcommntuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_pkey PRIMARY KEY (taguser, cauthor, cdate);


--
-- TOC entry 2039 (class 2606 OID 24711)
-- Name: tagpostuser tagpostuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_pkey PRIMARY KEY (taguser, pauthor, pdate);


--
-- TOC entry 2037 (class 2606 OID 24696)
-- Name: topic topic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pkey PRIMARY KEY (tname);


--
-- TOC entry 2029 (class 2606 OID 24638)
-- Name: userprofile userprofile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userprofile
    ADD CONSTRAINT userprofile_pkey PRIMARY KEY (login);


--
-- TOC entry 2033 (class 2606 OID 24656)
-- Name: userrel userrel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_pkey PRIMARY KEY (srcuser, tgtuser);


--
-- TOC entry 2045 (class 2606 OID 24672)
-- Name: commnt commnt_author_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_author_fkey FOREIGN KEY (author) REFERENCES public.userprofile(login);


--
-- TOC entry 2046 (class 2606 OID 24677)
-- Name: commnt commnt_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 2042 (class 2606 OID 24647)
-- Name: post post_author_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_author_fkey FOREIGN KEY (author) REFERENCES public.userprofile(login);


--
-- TOC entry 2051 (class 2606 OID 24727)
-- Name: tagcommntuser tagcommntuser_cauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_cauthor_fkey FOREIGN KEY (cauthor, cdate, cpauthor, cpdate) REFERENCES public.commnt(author, datestamp, pauthor, pdate);


--
-- TOC entry 2052 (class 2606 OID 24732)
-- Name: tagcommntuser tagcommntuser_taguser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_taguser_fkey FOREIGN KEY (taguser) REFERENCES public.userprofile(login);


--
-- TOC entry 2049 (class 2606 OID 24712)
-- Name: tagpostuser tagpostuser_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 2050 (class 2606 OID 24717)
-- Name: tagpostuser tagpostuser_taguser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_taguser_fkey FOREIGN KEY (taguser) REFERENCES public.userprofile(login);


--
-- TOC entry 2048 (class 2606 OID 24702)
-- Name: topic topic_cauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_cauthor_fkey FOREIGN KEY (cauthor, cpauthor, cpdate, cdate) REFERENCES public.commnt(author, pauthor, pdate, datestamp);


--
-- TOC entry 2047 (class 2606 OID 24697)
-- Name: topic topic_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 2043 (class 2606 OID 24657)
-- Name: userrel userrel_srcuser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_srcuser_fkey FOREIGN KEY (srcuser) REFERENCES public.userprofile(login);


--
-- TOC entry 2044 (class 2606 OID 24662)
-- Name: userrel userrel_tgtuser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_tgtuser_fkey FOREIGN KEY (tgtuser) REFERENCES public.userprofile(login);


-- Completed on 2019-08-22 09:51:00

--
-- PostgreSQL database dump complete
--
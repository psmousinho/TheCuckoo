--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.15
-- Dumped by pg_dump version 9.6.15

-- Started on 2019-09-13 09:00:02

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
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 16385)
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
-- TOC entry 186 (class 1259 OID 16388)
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
-- TOC entry 187 (class 1259 OID 16394)
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
-- TOC entry 188 (class 1259 OID 16397)
-- Name: tagpostuser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tagpostuser (
    pauthor character varying(31) NOT NULL,
    pdate timestamp without time zone NOT NULL,
    taguser character varying(31) NOT NULL
);


ALTER TABLE public.tagpostuser OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16400)
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
-- TOC entry 190 (class 1259 OID 16403)
-- Name: userprofile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.userprofile (
    login character varying(31) NOT NULL,
    passw character varying(255) NOT NULL,
    passhash character varying(255) DEFAULT '-1'::character varying,
    visibility boolean DEFAULT false NOT NULL,
    bio character varying(4095) DEFAULT 'Hello i am new to /cuckoo!!'::character varying NOT NULL,
    realname character varying(255) NOT NULL,
    nfollowers integer DEFAULT 0 NOT NULL,
    nfollowing integer DEFAULT 0 NOT NULL,
    lasttime timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.userprofile OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16412)
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
-- TOC entry 2032 (class 2606 OID 16416)
-- Name: commnt commnt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_pkey PRIMARY KEY (author, pauthor, pdate, datestamp);


--
-- TOC entry 2034 (class 2606 OID 16418)
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (author, datestamp);


--
-- TOC entry 2036 (class 2606 OID 16420)
-- Name: tagcommntuser tagcommntuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_pkey PRIMARY KEY (taguser, cauthor, cdate);


--
-- TOC entry 2038 (class 2606 OID 16422)
-- Name: tagpostuser tagpostuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_pkey PRIMARY KEY (taguser, pauthor, pdate);


--
-- TOC entry 2040 (class 2606 OID 16424)
-- Name: topic topic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pkey PRIMARY KEY (tname);


--
-- TOC entry 2042 (class 2606 OID 16426)
-- Name: userprofile userprofile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userprofile
    ADD CONSTRAINT userprofile_pkey PRIMARY KEY (login);


--
-- TOC entry 2044 (class 2606 OID 16428)
-- Name: userrel userrel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_pkey PRIMARY KEY (srcuser, tgtuser);


--
-- TOC entry 2045 (class 2606 OID 16429)
-- Name: commnt commnt_author_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_author_fkey FOREIGN KEY (author) REFERENCES public.userprofile(login);


--
-- TOC entry 2046 (class 2606 OID 16434)
-- Name: commnt commnt_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 2047 (class 2606 OID 16439)
-- Name: post post_author_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_author_fkey FOREIGN KEY (author) REFERENCES public.userprofile(login);


--
-- TOC entry 2048 (class 2606 OID 16444)
-- Name: tagcommntuser tagcommntuser_cauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_cauthor_fkey FOREIGN KEY (cauthor, cdate, cpauthor, cpdate) REFERENCES public.commnt(author, datestamp, pauthor, pdate);


--
-- TOC entry 2049 (class 2606 OID 16449)
-- Name: tagcommntuser tagcommntuser_taguser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_taguser_fkey FOREIGN KEY (taguser) REFERENCES public.userprofile(login);


--
-- TOC entry 2050 (class 2606 OID 16454)
-- Name: tagpostuser tagpostuser_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 2051 (class 2606 OID 16459)
-- Name: tagpostuser tagpostuser_taguser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_taguser_fkey FOREIGN KEY (taguser) REFERENCES public.userprofile(login);


--
-- TOC entry 2052 (class 2606 OID 16464)
-- Name: topic topic_cauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_cauthor_fkey FOREIGN KEY (cauthor, cpauthor, cpdate, cdate) REFERENCES public.commnt(author, pauthor, pdate, datestamp);


--
-- TOC entry 2053 (class 2606 OID 16469)
-- Name: topic topic_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 2054 (class 2606 OID 16474)
-- Name: userrel userrel_srcuser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_srcuser_fkey FOREIGN KEY (srcuser) REFERENCES public.userprofile(login);


--
-- TOC entry 2055 (class 2606 OID 16479)
-- Name: userrel userrel_tgtuser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_tgtuser_fkey FOREIGN KEY (tgtuser) REFERENCES public.userprofile(login);


-- Completed on 2019-09-13 09:00:03

--
-- PostgreSQL database dump complete
--


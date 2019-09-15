--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.3

-- Started on 2019-09-15 10:12:21 -03

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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 17100)
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
-- TOC entry 197 (class 1259 OID 17103)
-- Name: notifications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notifications (
    target character varying(31) NOT NULL,
    src character varying(31) NOT NULL,
    ndate timestamp without time zone NOT NULL,
    code smallint NOT NULL,
    pauthor character varying(31),
    pdate timestamp without time zone,
    cauthor character varying(31),
    cdate timestamp without time zone,
    cpauthor character varying(31),
    cpdate timestamp without time zone
);


ALTER TABLE public.notifications OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 17106)
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
-- TOC entry 199 (class 1259 OID 17112)
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
-- TOC entry 200 (class 1259 OID 17115)
-- Name: tagpostuser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tagpostuser (
    pauthor character varying(31) NOT NULL,
    pdate timestamp without time zone NOT NULL,
    taguser character varying(31) NOT NULL
);


ALTER TABLE public.tagpostuser OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17227)
-- Name: topic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topic (
    tname character varying(31) NOT NULL,
    tdate timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.topic OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17242)
-- Name: topiccomment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topiccomment (
    tname character varying(31) NOT NULL,
    cauthor character varying(31) NOT NULL,
    cpauthor character varying(31) NOT NULL,
    cdate timestamp without time zone NOT NULL,
    cpdate timestamp without time zone NOT NULL
);


ALTER TABLE public.topiccomment OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17267)
-- Name: topicpost; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topicpost (
    tname character varying(31) NOT NULL,
    pauthor character varying(31) NOT NULL,
    pdate timestamp without time zone NOT NULL
);


ALTER TABLE public.topicpost OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17121)
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
-- TOC entry 202 (class 1259 OID 17133)
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
-- TOC entry 3081 (class 2606 OID 17137)
-- Name: commnt commnt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_pkey PRIMARY KEY (author, pauthor, pdate, datestamp);


--
-- TOC entry 3083 (class 2606 OID 17139)
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (target, src, ndate);


--
-- TOC entry 3085 (class 2606 OID 17141)
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (author, datestamp);


--
-- TOC entry 3087 (class 2606 OID 17143)
-- Name: tagcommntuser tagcommntuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_pkey PRIMARY KEY (taguser, cauthor, cdate);


--
-- TOC entry 3089 (class 2606 OID 17145)
-- Name: tagpostuser tagpostuser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_pkey PRIMARY KEY (taguser, pauthor, pdate);


--
-- TOC entry 3095 (class 2606 OID 17231)
-- Name: topic topic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pkey PRIMARY KEY (tname);


--
-- TOC entry 3097 (class 2606 OID 17246)
-- Name: topiccomment topiccomment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topiccomment
    ADD CONSTRAINT topiccomment_pkey PRIMARY KEY (tname, cauthor, cpauthor, cdate, cpdate);


--
-- TOC entry 3099 (class 2606 OID 17271)
-- Name: topicpost topicpost_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topicpost
    ADD CONSTRAINT topicpost_pkey PRIMARY KEY (tname, pauthor, pdate);


--
-- TOC entry 3091 (class 2606 OID 17149)
-- Name: userprofile userprofile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userprofile
    ADD CONSTRAINT userprofile_pkey PRIMARY KEY (login);


--
-- TOC entry 3093 (class 2606 OID 17151)
-- Name: userrel userrel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_pkey PRIMARY KEY (srcuser, tgtuser);


--
-- TOC entry 3100 (class 2606 OID 17152)
-- Name: commnt commnt_author_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_author_fkey FOREIGN KEY (author) REFERENCES public.userprofile(login);


--
-- TOC entry 3101 (class 2606 OID 17157)
-- Name: commnt commnt_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commnt
    ADD CONSTRAINT commnt_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 3102 (class 2606 OID 17162)
-- Name: notifications notifications_cauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_cauthor_fkey FOREIGN KEY (cauthor, cdate, cpauthor, cpdate) REFERENCES public.commnt(author, datestamp, pauthor, pdate);


--
-- TOC entry 3103 (class 2606 OID 17167)
-- Name: notifications notifications_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 3104 (class 2606 OID 17172)
-- Name: notifications notifications_src_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_src_fkey FOREIGN KEY (src) REFERENCES public.userprofile(login);


--
-- TOC entry 3105 (class 2606 OID 17177)
-- Name: notifications notifications_target_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_target_fkey FOREIGN KEY (target) REFERENCES public.userprofile(login);


--
-- TOC entry 3106 (class 2606 OID 17182)
-- Name: post post_author_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_author_fkey FOREIGN KEY (author) REFERENCES public.userprofile(login);


--
-- TOC entry 3107 (class 2606 OID 17187)
-- Name: tagcommntuser tagcommntuser_cauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_cauthor_fkey FOREIGN KEY (cauthor, cdate, cpauthor, cpdate) REFERENCES public.commnt(author, datestamp, pauthor, pdate);


--
-- TOC entry 3108 (class 2606 OID 17192)
-- Name: tagcommntuser tagcommntuser_taguser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagcommntuser
    ADD CONSTRAINT tagcommntuser_taguser_fkey FOREIGN KEY (taguser) REFERENCES public.userprofile(login);


--
-- TOC entry 3109 (class 2606 OID 17197)
-- Name: tagpostuser tagpostuser_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 3110 (class 2606 OID 17202)
-- Name: tagpostuser tagpostuser_taguser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tagpostuser
    ADD CONSTRAINT tagpostuser_taguser_fkey FOREIGN KEY (taguser) REFERENCES public.userprofile(login);


--
-- TOC entry 3114 (class 2606 OID 17252)
-- Name: topiccomment topiccomment_cauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topiccomment
    ADD CONSTRAINT topiccomment_cauthor_fkey FOREIGN KEY (cauthor, cpauthor, cdate, cpdate) REFERENCES public.commnt(author, pauthor, datestamp, pdate);


--
-- TOC entry 3113 (class 2606 OID 17247)
-- Name: topiccomment topiccomment_tname_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topiccomment
    ADD CONSTRAINT topiccomment_tname_fkey FOREIGN KEY (tname) REFERENCES public.topic(tname);


--
-- TOC entry 3116 (class 2606 OID 17277)
-- Name: topicpost topicpost_pauthor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topicpost
    ADD CONSTRAINT topicpost_pauthor_fkey FOREIGN KEY (pauthor, pdate) REFERENCES public.post(author, datestamp);


--
-- TOC entry 3115 (class 2606 OID 17272)
-- Name: topicpost topicpost_tname_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topicpost
    ADD CONSTRAINT topicpost_tname_fkey FOREIGN KEY (tname) REFERENCES public.topic(tname);


--
-- TOC entry 3111 (class 2606 OID 17217)
-- Name: userrel userrel_srcuser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_srcuser_fkey FOREIGN KEY (srcuser) REFERENCES public.userprofile(login);


--
-- TOC entry 3112 (class 2606 OID 17222)
-- Name: userrel userrel_tgtuser_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.userrel
    ADD CONSTRAINT userrel_tgtuser_fkey FOREIGN KEY (tgtuser) REFERENCES public.userprofile(login);


-- Completed on 2019-09-15 10:12:21 -03

--
-- PostgreSQL database dump complete
--


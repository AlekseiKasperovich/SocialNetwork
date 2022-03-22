--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

-- Started on 2022-01-24 20:35:13

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

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 17185)
-- Name: communities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.communities (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE public.communities OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17324)
-- Name: communities_messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.communities_messages (
    id integer NOT NULL,
    posted timestamp without time zone NOT NULL,
    body character varying NOT NULL,
    sender_id integer NOT NULL,
    community_id integer NOT NULL
);


ALTER TABLE public.communities_messages OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 17199)
-- Name: events; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.events (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    author_id integer NOT NULL
);


ALTER TABLE public.events OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 17206)
-- Name: events_messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.events_messages (
    id integer NOT NULL,
    posted timestamp without time zone NOT NULL,
    body character varying NOT NULL,
    sender_id integer NOT NULL,
    event_id integer NOT NULL
);


ALTER TABLE public.events_messages OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 17213)
-- Name: friendships; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.friendships (
    id integer NOT NULL,
    sender_id integer NOT NULL,
    receiver_id integer NOT NULL,
    accepted boolean NOT NULL
);


ALTER TABLE public.friendships OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16611)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17218)
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    id integer NOT NULL,
    posted timestamp without time zone NOT NULL,
    body character varying NOT NULL,
    sender_id integer NOT NULL,
    receiver_id integer NOT NULL,
    is_private boolean NOT NULL
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17225)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17232)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    first_name character varying,
    last_name character varying,
    birthday date,
    phone character varying,
    status character varying NOT NULL,
    role_id integer NOT NULL,
    sex character varying
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17239)
-- Name: users_communities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_communities (
    user_id integer NOT NULL,
    community_id integer NOT NULL
);


ALTER TABLE public.users_communities OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17244)
-- Name: users_events; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_events (
    user_id integer NOT NULL,
    event_id integer NOT NULL
);


ALTER TABLE public.users_events OWNER TO postgres;

--
-- TOC entry 3384 (class 0 OID 17185)
-- Dependencies: 210
-- Data for Name: communities; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3393 (class 0 OID 17324)
-- Dependencies: 219
-- Data for Name: communities_messages; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3385 (class 0 OID 17199)
-- Dependencies: 211
-- Data for Name: events; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3386 (class 0 OID 17206)
-- Dependencies: 212
-- Data for Name: events_messages; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3387 (class 0 OID 17213)
-- Dependencies: 213
-- Data for Name: friendships; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3388 (class 0 OID 17218)
-- Dependencies: 214
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3389 (class 0 OID 17225)
-- Dependencies: 215
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.roles (id, name) VALUES (2, 'ROLE_USER');


--
-- TOC entry 3390 (class 0 OID 17232)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, email, password, first_name, last_name, birthday, phone, status, role_id, sex) VALUES (1, 'admin@gmail.com', '$2a$12$8TwI0onDZ.LoALxno1TCZeT2P3ayUdA9I5UsmDaCchCAIraGRm9JK', 'admin', 'adminov', '2000-01-01', '+375299999999', 'ACTIVE', 1, 'MALE');
INSERT INTO public.users (id, email, password, first_name, last_name, birthday, phone, status, role_id, sex) VALUES (2, 'user@gmail.com', '$2a$12$I3yuTCvlzJmVOez9q2njtODOXuxtSu3jxb2GRAoK5739WrmN9K0FO', 'user', 'userov', '1999-01-01', '+375291234567', 'ACTIVE', 2, 'MALE');


--
-- TOC entry 3391 (class 0 OID 17239)
-- Dependencies: 217
-- Data for Name: users_communities; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3392 (class 0 OID 17244)
-- Dependencies: 218
-- Data for Name: users_events; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3399 (class 0 OID 0)
-- Dependencies: 209
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 2, true);


--
-- TOC entry 3229 (class 2606 OID 17330)
-- Name: communities_messages communities_messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.communities_messages
    ADD CONSTRAINT communities_messages_pkey PRIMARY KEY (id);


--
-- TOC entry 3201 (class 2606 OID 17191)
-- Name: communities communities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.communities
    ADD CONSTRAINT communities_pkey PRIMARY KEY (id);


--
-- TOC entry 3205 (class 2606 OID 17212)
-- Name: events_messages events_messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events_messages
    ADD CONSTRAINT events_messages_pkey PRIMARY KEY (id);


--
-- TOC entry 3203 (class 2606 OID 17205)
-- Name: events events_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);


--
-- TOC entry 3207 (class 2606 OID 17217)
-- Name: friendships friendships_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friendships
    ADD CONSTRAINT friendships_pkey PRIMARY KEY (id);


--
-- TOC entry 3209 (class 2606 OID 17224)
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- TOC entry 3211 (class 2606 OID 17231)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3221 (class 2606 OID 17321)
-- Name: users_communities uk1xx2o0wbg9bg42ibaeboa4yqw; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_communities
    ADD CONSTRAINT uk1xx2o0wbg9bg42ibaeboa4yqw UNIQUE (community_id, user_id);


--
-- TOC entry 3225 (class 2606 OID 17323)
-- Name: users_events ukjxg6xs55h724niivu3jfqr4vu; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_events
    ADD CONSTRAINT ukjxg6xs55h724niivu3jfqr4vu UNIQUE (event_id, user_id);


--
-- TOC entry 3213 (class 2606 OID 17360)
-- Name: roles uni_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uni_name UNIQUE (name);


--
-- TOC entry 3215 (class 2606 OID 17358)
-- Name: users uniq_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uniq_email UNIQUE (email);


--
-- TOC entry 3217 (class 2606 OID 17356)
-- Name: users uniq_phone; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uniq_phone UNIQUE (phone);


--
-- TOC entry 3223 (class 2606 OID 17243)
-- Name: users_communities users_communities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_communities
    ADD CONSTRAINT users_communities_pkey PRIMARY KEY (community_id, user_id);


--
-- TOC entry 3227 (class 2606 OID 17248)
-- Name: users_events users_events_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_events
    ADD CONSTRAINT users_events_pkey PRIMARY KEY (user_id, event_id);


--
-- TOC entry 3219 (class 2606 OID 17238)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3230 (class 2606 OID 17259)
-- Name: events events_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3243 (class 2606 OID 17336)
-- Name: communities_messages fk_community_messag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.communities_messages
    ADD CONSTRAINT fk_community_messag FOREIGN KEY (community_id) REFERENCES public.communities(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3231 (class 2606 OID 17341)
-- Name: events_messages fk_event_messag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events_messages
    ADD CONSTRAINT fk_event_messag FOREIGN KEY (event_id) REFERENCES public.events(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3242 (class 2606 OID 17331)
-- Name: communities_messages fk_sender_messag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.communities_messages
    ADD CONSTRAINT fk_sender_messag FOREIGN KEY (sender_id) REFERENCES public.users(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3232 (class 2606 OID 17346)
-- Name: events_messages fk_user_event_messag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.events_messages
    ADD CONSTRAINT fk_user_event_messag FOREIGN KEY (sender_id) REFERENCES public.users(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3233 (class 2606 OID 17274)
-- Name: friendships friendships_receiver_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friendships
    ADD CONSTRAINT friendships_receiver_id_fkey FOREIGN KEY (receiver_id) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3234 (class 2606 OID 17279)
-- Name: friendships friendships_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friendships
    ADD CONSTRAINT friendships_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3235 (class 2606 OID 17284)
-- Name: messages messages_receiver_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_receiver_id_fkey FOREIGN KEY (receiver_id) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3236 (class 2606 OID 17289)
-- Name: messages messages_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3238 (class 2606 OID 17299)
-- Name: users_communities users_communities_community_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_communities
    ADD CONSTRAINT users_communities_community_id_fkey FOREIGN KEY (community_id) REFERENCES public.communities(id) NOT VALID;


--
-- TOC entry 3239 (class 2606 OID 17304)
-- Name: users_communities users_communities_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_communities
    ADD CONSTRAINT users_communities_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3240 (class 2606 OID 17309)
-- Name: users_events users_events_event_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_events
    ADD CONSTRAINT users_events_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.events(id) NOT VALID;


--
-- TOC entry 3241 (class 2606 OID 17314)
-- Name: users_events users_events_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_events
    ADD CONSTRAINT users_events_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3237 (class 2606 OID 17294)
-- Name: users users_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id) NOT VALID;


-- Completed on 2022-01-24 20:35:13

--
-- PostgreSQL database dump complete
--


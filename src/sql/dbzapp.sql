--
-- PostgreSQL database dump
--

-- Dumped from database version 12.12
-- Dumped by pg_dump version 12.12

-- Started on 2025-01-24 17:24:10

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
-- TOC entry 210 (class 1255 OID 59066)
-- Name: update_dateregister(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_dateregister() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.date_register := now();
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_dateregister() OWNER TO postgres;

--
-- TOC entry 211 (class 1255 OID 59067)
-- Name: update_dateupdate(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_dateupdate() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.date_update := now();
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_dateupdate() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 208 (class 1259 OID 59130)
-- Name: character; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."character" (
    character_id integer NOT NULL,
    character_name character varying(100) NOT NULL,
    character_age integer NOT NULL,
    character_desc text,
    character_image text,
    character_powerlevel integer,
    date_register timestamp without time zone,
    date_update timestamp without time zone
);


ALTER TABLE public."character" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 59138)
-- Name: character_character_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.character_character_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.character_character_id_seq OWNER TO postgres;

--
-- TOC entry 2870 (class 0 OID 0)
-- Dependencies: 209
-- Name: character_character_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.character_character_id_seq OWNED BY public."character".character_id;


--
-- TOC entry 202 (class 1259 OID 59082)
-- Name: gender; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gender (
    gender_id integer NOT NULL,
    gender_code character varying(2),
    gender_description character varying(50),
    date_register timestamp without time zone,
    date_update timestamp without time zone
);


ALTER TABLE public.gender OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 59087)
-- Name: jgeneros_idgenero_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jgeneros_idgenero_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.jgeneros_idgenero_seq OWNER TO postgres;

--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 203
-- Name: jgeneros_idgenero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jgeneros_idgenero_seq OWNED BY public.gender.gender_id;


--
-- TOC entry 204 (class 1259 OID 59091)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id integer NOT NULL,
    role_name character varying(100),
    rol_description character varying(100),
    rol_status boolean,
    date_register timestamp without time zone,
    date_update timestamp without time zone
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 59096)
-- Name: jroles_idrol_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jroles_idrol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.jroles_idrol_seq OWNER TO postgres;

--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 205
-- Name: jroles_idrol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jroles_idrol_seq OWNED BY public.role.role_id;


--
-- TOC entry 206 (class 1259 OID 59108)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_genderid bigint,
    user_roleid bigint,
    identification character varying(50),
    user_name character varying(250),
    user_lastname character varying(250),
    user_email character varying(100),
    user_password character varying(100),
    user_address character varying(500),
    user_status boolean,
    date_register timestamp without time zone,
    date_update timestamp without time zone
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 59126)
-- Name: jusuarios_idusuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.jusuarios_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.jusuarios_idusuario_seq OWNER TO postgres;

--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 207
-- Name: jusuarios_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.jusuarios_idusuario_seq OWNED BY public."user".user_id;


--
-- TOC entry 2712 (class 2604 OID 59145)
-- Name: character character_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."character" ALTER COLUMN character_id SET DEFAULT nextval('public.character_character_id_seq'::regclass);


--
-- TOC entry 2709 (class 2604 OID 59144)
-- Name: gender gender_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gender ALTER COLUMN gender_id SET DEFAULT nextval('public.jgeneros_idgenero_seq'::regclass);


--
-- TOC entry 2710 (class 2604 OID 59143)
-- Name: role role_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN role_id SET DEFAULT nextval('public.jroles_idrol_seq'::regclass);


--
-- TOC entry 2711 (class 2604 OID 59142)
-- Name: user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.jusuarios_idusuario_seq'::regclass);


--
-- TOC entry 2863 (class 0 OID 59130)
-- Dependencies: 208
-- Data for Name: character; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."character" (character_id, character_name, character_age, character_desc, character_image, character_powerlevel, date_register, date_update) FROM stdin;
1	Gohan	16	Gohan en Hispanoamérica, es uno de los personajes principales de los arcos argumentales de Dragon Ball Z, Dragon Ball Super y Dragon Ball GT. Es un mestizo entre saiyano y humano terrícola.	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/f5fb6f82-b077-4a23-a1d5-a23124f8a65e.webp?alt=media	789000	2025-01-24 22:21:17.746224	\N
2	Vegeta	40	Es el principe de los sayajines	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/fd22e8a0-87ea-4c87-b1d5-0d13d3f8c535.webp?alt=media	78000	2025-01-24 22:21:17.746224	\N
3	Piccolo	78	Es un Namekiano, un ser extraterrestre originario del planeta Namek	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/5777f643-8261-470d-bcd2-45a16efd8799.webp?alt=media	7878	2025-01-24 22:21:17.746224	\N
4	Bulma	40	Esposa de Vegeta	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/e65f9ce2-5f99-4d58-a3ae-bb495b9ba393.webp?alt=media	7894	2025-01-24 22:21:17.746224	\N
5	Goku	30	Goku es el personaje principal del Anime Dragon Ball Z	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/71c37523-3eda-4479-86bd-9d1f76f976de.webp?alt=media	1500	2025-01-24 22:21:17.746224	\N
6	Dodoria	85	Es un guerrero despiadado y sirve junto a Zabon como uno de los dos generales de mayor rango y mano derecha de Freezer.	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/39b69a1a-9aee-480d-859a-933f6640a846.webp?alt=media	22000	2025-01-24 22:21:17.746224	\N
7	Ginuy	790	Es un integrante y a su vez líder del grupo de mercenarios de las Fuerzas Especiales Ginew en el Ejército de Freezer que lleva su nombre en su honor.	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/70fd34dd-d3cd-4e10-a94c-065cc7bb2041.webp?alt=media	120000	2025-01-24 22:21:17.746224	\N
8	Freezer	150	Es un alienígena mutante, brutal dictador y líder del Imperio Galáctico del Universo 7 con el apoyo estratégico en secreto de su padre Cold.	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/2d3fde08-1b86-4bfc-8268-87b1f2334803.webp?alt=media	84000000	2025-01-24 22:21:17.746224	\N
9	Zarbon	789	Conocido también como Zabon, es un soldado de alto rango dentro del Ejército de Freezer siendo junto a Dodoria la mano derecha de Freezer.	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/ad4449e4-790a-4c36-9d33-349474983624.webp?alt=media	23000	2025-01-24 22:21:17.746224	\N
10	Cell	34	Es un bioandroide creado por la computadora del Dr. Gero, quien vino del futuro de la línea 3 con la intención de vengarse de Goku por haber acabado con el Ejército del Listón Rojo, y con ello el sueño de todo villano: dominar el mundo.	https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/ca887e1f-1ca8-4bc6-9cf4-e5622759a608.webp?alt=media	980000000	2025-01-24 22:21:17.746224	\N
\.


--
-- TOC entry 2857 (class 0 OID 59082)
-- Dependencies: 202
-- Data for Name: gender; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gender (gender_id, gender_code, gender_description, date_register, date_update) FROM stdin;
1	M	Masculino	2025-01-24 17:26:04.093158	2025-01-24 17:26:04.093158
\.


--
-- TOC entry 2859 (class 0 OID 59091)
-- Dependencies: 204
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (role_id, role_name, rol_description, rol_status, date_register, date_update) FROM stdin;
1	admin	Admin	t	2025-01-24 17:25:22.436448	\N
2	client	Client	t	2025-01-24 22:08:11.614983	\N
\.


--
-- TOC entry 2861 (class 0 OID 59108)
-- Dependencies: 206
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (user_id, user_genderid, user_roleid, identification, user_name, user_lastname, user_email, user_password, user_address, user_status, date_register, date_update) FROM stdin;
1	1	1	1003866173	Andy	Quinche	laqm_14@hotmail.com	$2y$10$cdI6005pPt1G79XJGeUAc.zP5nfxlkv7TMFnqfWaRC34i1ICfLY.e	Otavalo	t	2025-01-24 17:28:08.633201	\N
2	1	2	1716247943	Luis	Moran	lmoran@gmail.com	$2y$10$cdI6005pPt1G79XJGeUAc.zP5nfxlkv7TMFnqfWaRC34i1ICfLY.e	Otavalo	t	2025-01-24 22:10:10.437437	\N
\.


--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 209
-- Name: character_character_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.character_character_id_seq', 10, true);


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 203
-- Name: jgeneros_idgenero_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jgeneros_idgenero_seq', 1, true);


--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 205
-- Name: jroles_idrol_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jroles_idrol_seq', 2, true);


--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 207
-- Name: jusuarios_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jusuarios_idusuario_seq', 2, true);


--
-- TOC entry 2720 (class 2606 OID 59137)
-- Name: character character_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."character"
    ADD CONSTRAINT character_pkey PRIMARY KEY (character_id);


--
-- TOC entry 2714 (class 2606 OID 59086)
-- Name: gender pk_gender_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gender
    ADD CONSTRAINT pk_gender_id PRIMARY KEY (gender_id);


--
-- TOC entry 2716 (class 2606 OID 59095)
-- Name: role pk_role_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT pk_role_id PRIMARY KEY (role_id);


--
-- TOC entry 2718 (class 2606 OID 59115)
-- Name: user pk_user_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT pk_user_id PRIMARY KEY (user_id);


--
-- TOC entry 2729 (class 2620 OID 59141)
-- Name: character tr_update_dateregister_character; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_update_dateregister_character BEFORE INSERT ON public."character" FOR EACH ROW EXECUTE FUNCTION public.update_dateregister();


--
-- TOC entry 2723 (class 2620 OID 59089)
-- Name: gender tr_update_dateregister_gender; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_update_dateregister_gender BEFORE INSERT ON public.gender FOR EACH ROW EXECUTE FUNCTION public.update_dateregister();


--
-- TOC entry 2725 (class 2620 OID 59099)
-- Name: role tr_update_dateregister_role; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_update_dateregister_role BEFORE INSERT ON public.role FOR EACH ROW EXECUTE FUNCTION public.update_dateregister();


--
-- TOC entry 2727 (class 2620 OID 59129)
-- Name: user tr_update_dateregister_user; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_update_dateregister_user BEFORE INSERT ON public."user" FOR EACH ROW EXECUTE FUNCTION public.update_dateregister();


--
-- TOC entry 2730 (class 2620 OID 59140)
-- Name: character tr_update_dateupdate_character; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_update_dateupdate_character BEFORE UPDATE ON public."character" FOR EACH ROW EXECUTE FUNCTION public.update_dateupdate();


--
-- TOC entry 2724 (class 2620 OID 59090)
-- Name: gender tr_update_dateupdate_gender; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_update_dateupdate_gender BEFORE INSERT ON public.gender FOR EACH ROW EXECUTE FUNCTION public.update_dateupdate();


--
-- TOC entry 2726 (class 2620 OID 59098)
-- Name: role tr_update_dateupdate_role; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_update_dateupdate_role BEFORE UPDATE ON public.role FOR EACH ROW EXECUTE FUNCTION public.update_dateupdate();


--
-- TOC entry 2728 (class 2620 OID 59128)
-- Name: user tr_update_dateupdate_user; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER tr_update_dateupdate_user BEFORE UPDATE ON public."user" FOR EACH ROW EXECUTE FUNCTION public.update_dateupdate();


--
-- TOC entry 2721 (class 2606 OID 59116)
-- Name: user fk_jusuario_jgeneros__jgeneros; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk_jusuario_jgeneros__jgeneros FOREIGN KEY (user_genderid) REFERENCES public.gender(gender_id);


--
-- TOC entry 2722 (class 2606 OID 59121)
-- Name: user fk_jusuario_jroles_ju_jroles; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fk_jusuario_jroles_ju_jroles FOREIGN KEY (user_roleid) REFERENCES public.role(role_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2025-01-24 17:24:11

--
-- PostgreSQL database dump complete
--


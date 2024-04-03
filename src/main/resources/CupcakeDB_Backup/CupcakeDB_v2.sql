--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-04-03 07:39:34 UTC

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
-- TOC entry 216 (class 1259 OID 16393)
-- Name: bottom; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bottom (
    "bottomID" integer NOT NULL,
    name character varying(30),
    price integer NOT NULL
);


ALTER TABLE public.bottom OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16405)
-- Name: bottom_bottomID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."bottom_bottomID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."bottom_bottomID_seq" OWNER TO postgres;

--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 219
-- Name: bottom_bottomID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."bottom_bottomID_seq" OWNED BY public.bottom."bottomID";


--
-- TOC entry 3224 (class 2604 OID 16406)
-- Name: bottom bottomID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom ALTER COLUMN "bottomID" SET DEFAULT nextval('public."bottom_bottomID_seq"'::regclass);


--
-- TOC entry 3370 (class 0 OID 16393)
-- Dependencies: 216
-- Data for Name: bottom; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bottom VALUES (1, 'Almond', 7);
INSERT INTO public.bottom VALUES (2, 'Pistacio', 6);
INSERT INTO public.bottom VALUES (3, 'Nutmeg', 5);
INSERT INTO public.bottom VALUES (4, 'Vanilla', 5);
INSERT INTO public.bottom VALUES (5, 'Chocolate', 5);


--
-- TOC entry 3378 (class 0 OID 0)
-- Dependencies: 219
-- Name: bottom_bottomID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."bottom_bottomID_seq"', 5, true);


--
-- TOC entry 3226 (class 2606 OID 16411)
-- Name: bottom bottom_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom
    ADD CONSTRAINT bottom_pkey PRIMARY KEY ("bottomID");


-- Completed on 2024-04-03 07:39:34 UTC

--
-- PostgreSQL database dump complete
--


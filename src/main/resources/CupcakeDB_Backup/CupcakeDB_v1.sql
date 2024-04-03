--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-04-02 08:18:56 UTC

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
-- TOC entry 3398 (class 0 OID 0)
-- Dependencies: 219
-- Name: bottom_bottomID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."bottom_bottomID_seq" OWNED BY public.bottom."bottomID";


--
-- TOC entry 218 (class 1259 OID 16402)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
                               "orderID" integer NOT NULL,
                               "isPaidFor" boolean,
                               price integer,
                               amount integer,
                               "userID" integer NOT NULL,
                               "bottomID" integer NOT NULL,
                               "toppingID" integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16438)
-- Name: orders_bottomID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."orders_bottomID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."orders_bottomID_seq" OWNER TO postgres;

--
-- TOC entry 3399 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_bottomID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orders_bottomID_seq" OWNED BY public.orders."bottomID";


--
-- TOC entry 222 (class 1259 OID 16428)
-- Name: orders_orderID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."orders_orderID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."orders_orderID_seq" OWNER TO postgres;

--
-- TOC entry 3400 (class 0 OID 0)
-- Dependencies: 222
-- Name: orders_orderID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orders_orderID_seq" OWNED BY public.orders."orderID";


--
-- TOC entry 225 (class 1259 OID 16443)
-- Name: orders_toppingID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."orders_toppingID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."orders_toppingID_seq" OWNER TO postgres;

--
-- TOC entry 3401 (class 0 OID 0)
-- Dependencies: 225
-- Name: orders_toppingID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orders_toppingID_seq" OWNED BY public.orders."toppingID";


--
-- TOC entry 223 (class 1259 OID 16433)
-- Name: orders_userID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."orders_userID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."orders_userID_seq" OWNER TO postgres;

--
-- TOC entry 3402 (class 0 OID 0)
-- Dependencies: 223
-- Name: orders_userID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orders_userID_seq" OWNED BY public.orders."userID";


--
-- TOC entry 215 (class 1259 OID 16390)
-- Name: topping; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topping (
                                "toppingID" integer NOT NULL,
                                name character varying(30),
                                price integer NOT NULL
);


ALTER TABLE public.topping OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16412)
-- Name: topping_toppingID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."topping_toppingID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."topping_toppingID_seq" OWNER TO postgres;

--
-- TOC entry 3403 (class 0 OID 0)
-- Dependencies: 220
-- Name: topping_toppingID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."topping_toppingID_seq" OWNED BY public.topping."toppingID";


--
-- TOC entry 217 (class 1259 OID 16399)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
                              "userID" integer NOT NULL,
                              email character varying(255),
                              password character varying(255),
                              role character varying(8)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16419)
-- Name: users_userID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."users_userID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."users_userID_seq" OWNER TO postgres;

--
-- TOC entry 3404 (class 0 OID 0)
-- Dependencies: 221
-- Name: users_userID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."users_userID_seq" OWNED BY public.users."userID";


--
-- TOC entry 3222 (class 2604 OID 16406)
-- Name: bottom bottomID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom ALTER COLUMN "bottomID" SET DEFAULT nextval('public."bottom_bottomID_seq"'::regclass);


--
-- TOC entry 3224 (class 2604 OID 16429)
-- Name: orders orderID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN "orderID" SET DEFAULT nextval('public."orders_orderID_seq"'::regclass);


--
-- TOC entry 3225 (class 2604 OID 16434)
-- Name: orders userID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN "userID" SET DEFAULT nextval('public."orders_userID_seq"'::regclass);


--
-- TOC entry 3226 (class 2604 OID 16439)
-- Name: orders bottomID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN "bottomID" SET DEFAULT nextval('public."orders_bottomID_seq"'::regclass);


--
-- TOC entry 3227 (class 2604 OID 16444)
-- Name: orders toppingID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN "toppingID" SET DEFAULT nextval('public."orders_toppingID_seq"'::regclass);


--
-- TOC entry 3221 (class 2604 OID 16413)
-- Name: topping toppingID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topping ALTER COLUMN "toppingID" SET DEFAULT nextval('public."topping_toppingID_seq"'::regclass);


--
-- TOC entry 3223 (class 2604 OID 16420)
-- Name: users userID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN "userID" SET DEFAULT nextval('public."users_userID_seq"'::regclass);


--
-- TOC entry 3383 (class 0 OID 16393)
-- Dependencies: 216
-- Data for Name: bottom; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3385 (class 0 OID 16402)
-- Dependencies: 218
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3382 (class 0 OID 16390)
-- Dependencies: 215
-- Data for Name: topping; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3384 (class 0 OID 16399)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 219
-- Name: bottom_bottomID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."bottom_bottomID_seq"', 1, false);


--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_bottomID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orders_bottomID_seq"', 1, false);


--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 222
-- Name: orders_orderID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orders_orderID_seq"', 1, false);


--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 225
-- Name: orders_toppingID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orders_toppingID_seq"', 1, false);


--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 223
-- Name: orders_userID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orders_userID_seq"', 1, false);


--
-- TOC entry 3410 (class 0 OID 0)
-- Dependencies: 220
-- Name: topping_toppingID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."topping_toppingID_seq"', 1, false);


--
-- TOC entry 3411 (class 0 OID 0)
-- Dependencies: 221
-- Name: users_userID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."users_userID_seq"', 1, false);


--
-- TOC entry 3231 (class 2606 OID 16411)
-- Name: bottom bottom_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom
    ADD CONSTRAINT bottom_pkey PRIMARY KEY ("bottomID");


--
-- TOC entry 3235 (class 2606 OID 16449)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY ("orderID");


--
-- TOC entry 3229 (class 2606 OID 16418)
-- Name: topping topping_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topping
    ADD CONSTRAINT topping_pkey PRIMARY KEY ("toppingID");


--
-- TOC entry 3233 (class 2606 OID 16425)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("userID");


--
-- TOC entry 3236 (class 2606 OID 16455)
-- Name: orders bottomID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "bottomID_fkey" FOREIGN KEY ("bottomID") REFERENCES public.bottom("bottomID") NOT VALID;


--
-- TOC entry 3237 (class 2606 OID 16460)
-- Name: orders toppingID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "toppingID_fkey" FOREIGN KEY ("toppingID") REFERENCES public.topping("toppingID") NOT VALID;


--
-- TOC entry 3238 (class 2606 OID 16450)
-- Name: orders userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "userID_fkey" FOREIGN KEY ("userID") REFERENCES public.users("userID") NOT VALID;


-- Completed on 2024-04-02 08:18:56 UTC

--
-- PostgreSQL database dump complete
--

